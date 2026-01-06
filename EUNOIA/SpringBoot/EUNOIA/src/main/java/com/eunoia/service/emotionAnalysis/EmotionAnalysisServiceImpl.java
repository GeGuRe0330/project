package com.eunoia.service.emotionAnalysis;

import com.eunoia.common.error.CustomException;
import com.eunoia.common.error.ErrorCode;
import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.dto.emotionAnalysis.EmotionAnalysisResponseDTO;
import com.eunoia.dto.emotionAnalysis.EmotionScoreDataDTO;
import com.eunoia.gptapi.GPTService;
import com.eunoia.repository.EmotionAnalysisRepository;
import com.eunoia.security.CurrentUserService;
import com.eunoia.security.OwnershipService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmotionAnalysisServiceImpl implements EmotionAnalysisService {

    private final EmotionAnalysisRepository analysisRepository;
    private final GPTService gptService;
    private final CurrentUserService currentUserService;
    private final OwnershipService ownershipService;

    @Override
    @Transactional(readOnly = true)
    public EmotionAnalysisResponseDTO getLatestAnalysisForMe() {
        Long memberId = currentUserService.getCurrentMemberId();

        EmotionAnalysis analysis = analysisRepository.findTopByEmotionEntry_Member_IdOrderByCreatedAtDesc(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "아직 분석 결과가 없습니다."));

        return EmotionAnalysisResponseDTO.from(analysis);
    }

    @Override
    @Transactional
    public EmotionAnalysisResponseDTO updateAnalysis(Long entryId) {
        EmotionEntry entry = ownershipService.validateEntryOwnership(entryId);

        EmotionAnalysis analysis = analysisRepository.findByEmotionEntry(entry)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "요청한 분석내용을 찾지 못했습니다."));

        // GPT 분석 호출
        JsonNode result = gptService.analyzeEmotion(entry.getContent());

        // GPT 응답 파싱 및 반영
        analysis.setEmotionDetected(result.get("emotionDetected").asText());
        analysis.setKeywords(result.get("keywords").asText());
        analysis.setInsightSummary(result.get("insightSummary").asText());
        analysis.setFlowHint(result.get("flowHint").asText());
        analysis.setEmotionSummary(result.get("emotionSummary").asText());
        analysis.setEmotionScore(result.get("emotionScore").asDouble());

        EmotionAnalysis updated = analysisRepository.save(analysis);

        return EmotionAnalysisResponseDTO.from(updated);
    }

    @Override
    @Transactional
    public EmotionAnalysisResponseDTO createWarmMessages(Long entryId) {
        EmotionEntry entry = ownershipService.validateEntryOwnership(entryId);

        List<String> warmMessages = gptService.generateWarmMessages(entry.getContent());

        EmotionAnalysis analysis = analysisRepository.findByEmotionEntry(entry)
                .orElseGet(() -> EmotionAnalysis.builder()
                        .emotionEntry(entry)
                        .build());

        analysis.setWarmMessages(warmMessages);

        EmotionAnalysis saved = analysisRepository.save(analysis);
        return EmotionAnalysisResponseDTO.from(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getWarmMessagesByEntryId(Long entryId) {
        EmotionEntry entry = ownershipService.validateEntryOwnership(entryId);

        EmotionAnalysis analysis = analysisRepository.findByEmotionEntry(entry)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "아직 따뜻한 말이 준비되지 않았습니다."));

        return analysis.getWarmMessages();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmotionScoreDataDTO> getEmotionScoresForMe() {
        Long memberId = currentUserService.getCurrentMemberId();

        Pageable pageable = PageRequest.of(0, 7);
        List<EmotionScoreDataDTO> scores = analysisRepository.findEmotionScoresByMemberId(memberId, pageable);

        Collections.reverse(scores);

        return scores;
    }

}
