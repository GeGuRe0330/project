package com.eunoia.service.meta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.domain.Member;
import com.eunoia.domain.MetaAnalysisResult;
import com.eunoia.dto.meta.MetaAnalysisLatestResponseDTO;
import com.eunoia.dto.meta.MetaAnalysisStatusDTO;
import com.eunoia.gptapi.GPTService;
import com.eunoia.repository.EmotionAnalysisRepository;
import com.eunoia.repository.MetaAnalysisResultRepository;
import com.eunoia.security.CurrentUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MetaAnalysisServiceImpl implements MetaAnalysisService {

    private static final int REQUIRED_COUNT = 10;
    private static final int MIN_SCORE = 80;

    private final EmotionAnalysisRepository emotionAnalysisRepository;
    private final MetaAnalysisResultRepository metaAnalysisResultRepository;
    private final CurrentUserService currentUserService;
    private final GPTService gptService;
    private final ObjectMapper objectMapper;

    @Override
    public MetaAnalysisLatestResponseDTO getLatestForMe() {
        Member me = currentUserService.getCurrentMember();

        LocalDate periodEnd = LocalDate.now();
        LocalDate periodStart = periodEnd.minusDays(29);

        return metaAnalysisResultRepository.findByMember(me)
                .map(result -> {
                    JsonNode node = parseJsonOrNull(result.getResultJson());

                    return MetaAnalysisLatestResponseDTO.builder()
                            .status(MetaAnalysisStatusDTO.READY)
                            .periodStart(result.getPeriodStart())
                            .periodEnd(result.getPeriodEnd())
                            .requiredCount(REQUIRED_COUNT)
                            .currentCount(result.getBasedOnCount())
                            .actions(List.of())
                            .resultJson(node)
                            .createdAt(result.getCreatedAt())
                            .updatedAt(result.getUpdatedAt())
                            .build();
                })
                .orElseGet(() -> {
                    int currentCount = countReadyEntries(me, periodStart, periodEnd, MIN_SCORE);

                    return MetaAnalysisLatestResponseDTO.builder()
                            .status(resolveStatus(currentCount))
                            .periodStart(periodStart)
                            .periodEnd(periodEnd)
                            .requiredCount(REQUIRED_COUNT)
                            .currentCount(currentCount)
                            .actions(defaultActions())
                            .resultJson(null)
                            .createdAt(null)
                            .updatedAt(null)
                            .build();
                });
    }

    @Override
    @Transactional
    public MetaAnalysisLatestResponseDTO upsertLatestForMe() {
        Member me = currentUserService.getCurrentMember();

        LocalDate periodEnd = LocalDate.now();
        LocalDate periodStart = periodEnd.minusDays(29);

        List<EmotionAnalysis> candidates = emotionAnalysisRepository.findCandidateAnalysesForMeta(
                me, periodStart, periodEnd, MIN_SCORE);

        List<EmotionAnalysis> pickedAnalyses = pickOnePerDayUpToLimitAnalyses(candidates, REQUIRED_COUNT);
        int currentCount = pickedAnalyses.size();

        if (currentCount < REQUIRED_COUNT) {
            return MetaAnalysisLatestResponseDTO.builder()
                    .status(resolveStatus(currentCount))
                    .periodStart(periodStart)
                    .periodEnd(periodEnd)
                    .requiredCount(REQUIRED_COUNT)
                    .currentCount(currentCount)
                    .actions(defaultActions())
                    .resultJson(null)
                    .createdAt(null)
                    .updatedAt(null)
                    .build();
        }

        List<EmotionEntry> pickedEntries = pickedAnalyses.stream()
                .map(EmotionAnalysis::getEmotionEntry)
                .toList();

        String metaInput = buildMetaInput(pickedEntries);

        // GPT 메타 분석 호출
        JsonNode metaResult = gptService.analyzeMeta(metaInput);

        // metaResult의 타입을 ObjectNode로 보장
        ObjectNode root = ensureObjectNode(metaResult);

        // 기존 서버에 있는 데이터를 출력 결과 중 일부에 병합하는 과정
        List<RepresentativeEntry> rep = buildRepresentativeEntriesFromAnalyses(pickedAnalyses);

        ObjectNode evidence = root.withObject("evidenceNarrative");

        evidence.putPOJO("howChosen", rep);

        // metaClarityScore 추출 (DB에 따로 정해진 컬럼에 저장하기 위해)
        int metaClarityScore = root.path("clarity").path("clarityScore").asInt(0);

        // JSON타입을 DB에는 문자열로 저장
        String resultJsonStr = root.toString();

        // DB 저장
        MetaAnalysisResult saved = upsertMetaResult(
                me, periodStart, periodEnd, currentCount, metaClarityScore, resultJsonStr);

        // 응답은 String이 아니라 "root(JsonNode)" 그대로 내려주기
        return MetaAnalysisLatestResponseDTO.builder()
                .status(MetaAnalysisStatusDTO.READY)
                .periodStart(saved.getPeriodStart())
                .periodEnd(saved.getPeriodEnd())
                .requiredCount(REQUIRED_COUNT)
                .currentCount(saved.getBasedOnCount())
                .actions(List.of())
                .resultJson(root)
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();
    }

    // ---------------------------
    // 유틸
    // ---------------------------

    private JsonNode parseJsonOrNull(String json) {
        if (json == null || json.isBlank())
            return null;
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private ObjectNode ensureObjectNode(JsonNode node) {
        if (node instanceof ObjectNode obj) {
            return obj;
        }
        // fallback
        return objectMapper.createObjectNode();
    }

    private List<EmotionAnalysis> pickOnePerDayUpToLimitAnalyses(List<EmotionAnalysis> candidates, int limit) {
        if (candidates == null || candidates.isEmpty()) {
            return List.of();
        }

        Map<LocalDate, EmotionAnalysis> perDay = new LinkedHashMap<>();

        for (EmotionAnalysis a : candidates) {
            EmotionEntry e = a.getEmotionEntry();
            if (e == null)
                continue;

            LocalDate d = e.getEntryDate();
            if (d == null)
                continue;

            perDay.putIfAbsent(d, a);

            if (perDay.size() >= limit)
                break;
        }

        return new ArrayList<>(perDay.values());
    }

    private int countReadyEntries(Member member, LocalDate start, LocalDate end, int minScore) {
        List<EmotionAnalysis> candidates = emotionAnalysisRepository.findCandidateAnalysesForMeta(member, start, end,
                minScore);

        return pickOnePerDayUpToLimitAnalyses(candidates, REQUIRED_COUNT).size();
    }

    private MetaAnalysisStatusDTO resolveStatus(int currentCount) {
        if (currentCount < REQUIRED_COUNT)
            return MetaAnalysisStatusDTO.PREPARING;
        return MetaAnalysisStatusDTO.READY;
    }

    private List<String> defaultActions() {
        return List.of(
                "당신의 모습이 가장 잘 담긴 감정글을 선별하기에 기록 준비도가 채워지지 않을수 있어요.",
                "어떤 순간에 대한 사실과 함께 그 순간 본인의 감정에 대해 집중해서 적어보아요.",
                "짧아도 괜찮아요. 꾸준히 남긴 기록이 거울을 선명하게 만들어요.");
    }

    private MetaAnalysisResult upsertMetaResult(
            Member member,
            LocalDate periodStart,
            LocalDate periodEnd,
            int basedOnCount,
            int metaClarityScore,
            String resultJson) {

        MetaAnalysisResult entity = metaAnalysisResultRepository.findByMember(member)
                .orElseGet(() -> {
                    MetaAnalysisResult created = new MetaAnalysisResult();
                    created.setMember(member);
                    return created;
                });

        entity.setPeriodStart(periodStart);
        entity.setPeriodEnd(periodEnd);
        entity.setBasedOnCount(basedOnCount);
        entity.setMetaClarityScore(metaClarityScore);
        entity.setResultJson(resultJson);

        return metaAnalysisResultRepository.save(entity);
    }

    private String normalizeContent(String content, int maxChars) {
        if (content == null)
            return "";

        String normalized = content
                .replace("\r\n", "\n")
                .replace("\t", " ")
                .trim();

        if (normalized.length() <= maxChars)
            return normalized;

        return normalized.substring(0, maxChars) + "\n...(생략)";
    }

    private String buildMetaInput(List<EmotionEntry> picked) {
        final int MAX_CHARS_PER_ENTRY = 1000;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < picked.size(); i++) {
            EmotionEntry e = picked.get(i);

            sb.append("[ENTRY #").append(i + 1).append("]\n");
            sb.append(normalizeContent(e.getContent(), MAX_CHARS_PER_ENTRY)).append("\n\n");
        }
        return sb.toString();
    }

    private record RepresentativeEntry(Long entryId, String whySelected) {
    }

    private List<RepresentativeEntry> buildRepresentativeEntriesFromAnalyses(List<EmotionAnalysis> pickedAnalyses) {
        List<RepresentativeEntry> list = new ArrayList<>();
        for (EmotionAnalysis a : pickedAnalyses) {
            Long entryId = a.getEmotionEntry().getId();
            String why = a.getEntryClarityReason();
            if (why == null || why.isBlank()) {
                why = "감정의 흐름과 맥락이 비교적 선명하게 드러나 있어요.";
            }
            list.add(new RepresentativeEntry(entryId, why));
        }
        return list;
    }
}
