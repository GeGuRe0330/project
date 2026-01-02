package com.eunoia.service;

import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.dto.EmotionAnalysisRequestDTO;
import com.eunoia.dto.EmotionAnalysisResponseDTO;
import com.eunoia.dto.EmotionScoreDataDTO;
import com.eunoia.gptapi.GPTService;
import com.eunoia.repository.EmotionAnalysisRepository;
import com.eunoia.repository.EmotionEntryRepository;
import com.eunoia.security.CurrentUserService;
import com.eunoia.security.OwnershipService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmotionAnalysisServiceImpl implements EmotionAnalysisService {

    private final EmotionAnalysisRepository analysisRepository;
    private final EmotionEntryRepository entryRepository;
    private final GPTService gptService;
    private final CurrentUserService currentUserService;
    private final OwnershipService ownershipService;

    // @Override
    // public EmotionAnalysisResponseDTO createAnalysis(Long entryId,
    // EmotionAnalysisRequestDTO dto) {
    // EmotionEntry entry = entryRepository.findById(entryId)
    // .orElseThrow(() -> new EntityNotFoundException("분석 대상 감정 글이 존재하지 않습니다. ID: "
    // + entryId));
    //
    // EmotionAnalysis analysis = EmotionAnalysis.builder()
    // .emotionEntry(entry)
    // .emotionDetected(dto.getEmotionDetected())
    // .keywords(dto.getKeywords())
    // .insightSummary(dto.getInsightSummary())
    // .flowHint(dto.getFlowHint())
    // .build();
    //
    // return EmotionAnalysisResponseDTO.from(analysisRepository.save(analysis));
    // }

    @Override
    public EmotionAnalysisResponseDTO getAnalysisById(Long id) {
        EmotionAnalysis analysis = analysisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("감정 분석 결과가 없습니다. ID: " + id));
        return EmotionAnalysisResponseDTO.from(analysis);
    }

    // 이거 바꾸기!
    @Override
    public List<EmotionAnalysisResponseDTO> getAllAnalysesByEntry(Long entryId) {
        EmotionEntry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new EntityNotFoundException("감정 글을 찾을 수 없습니다. ID: " + entryId));

        return analysisRepository.findByEmotionEntry(entry)
                .stream()
                .map(EmotionAnalysisResponseDTO::from)
                .collect(Collectors.toList());
    }

    // 이거도!
    @Override
    public EmotionAnalysisResponseDTO updateAnalysis(Long id, EmotionAnalysisRequestDTO dto) {
        EmotionAnalysis analysis = analysisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("감정 분석을 찾을 수 없습니다. ID: " + id));

        EmotionEntry entry = ownershipService.validateEntryOwnership(analysis.getEmotionEntry().getId()); // 분석에 들어갈 감정글
        String content = entry.getContent();

        // GPT 분석 호출
        JsonNode result = gptService.analyzeEmotion(content);

        // GPT 응답 파싱 및 반영
        analysis.setEmotionDetected(result.get("emotionDetected").asText());
        analysis.setKeywords(result.get("keywords").asText());
        analysis.setInsightSummary(result.get("insightSummary").asText());
        analysis.setFlowHint(result.get("flowHint").asText());
        analysis.setEmotionSummary(result.get("emotionSummary").asText());
        analysis.setEmotionScore(result.get("emotionScore").asDouble());

        // // 따뜻한 말도 업데이트할 수 있게 허용
        // if (dto.getWarmMessages() != null && !dto.getWarmMessages().isEmpty()) {
        // analysis.setWarmMessages(dto.getWarmMessages());
        // }

        EmotionAnalysis updated = analysisRepository.save(analysis);

        return EmotionAnalysisResponseDTO.from(updated);
    }

    @Override
    public void deleteAnalysis(Long id) {
        if (!analysisRepository.existsById(id)) {
            throw new EntityNotFoundException("삭제할 분석 결과가 없습니다. ID: " + id);
        }
        analysisRepository.deleteById(id);
    }

    // 수정완!
    @Override
    public EmotionAnalysisResponseDTO createWarmMessages(Long entryId) {
        EmotionEntry entry = ownershipService.validateEntryOwnership(entryId);

        // GPT에게 요청
        List<String> warmMessages = gptService.generateWarmMessages(entry.getContent());

        EmotionAnalysis analysis = EmotionAnalysis.builder()
                .emotionEntry(entry)
                .warmMessages(warmMessages)
                .build();

        EmotionAnalysis saved = analysisRepository.save(analysis);

        return EmotionAnalysisResponseDTO.from(saved);
    }

    @Override
    public List<String> getWarmMessagesByEntryId(Long entryId) {
        EmotionAnalysis analysis = analysisRepository.findAll().stream()
                .filter(a -> a.getEmotionEntry().getId().equals(entryId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("해당 감정글에 대한 분석이 없습니다. ID: " + entryId));

        return analysis.getWarmMessages();
    }

    // 감정 점수 가져오기 (그래프)
    // TODO : 구버전
    // @Override
    // public List<EmotionScoreDataDTO> getEmotionScoresByMember(Long memberId) {
    // return analysisRepository.findEmotionScoresByMemberId(memberId);
    // }

    // TODO : 신버전
    @Override
    public List<EmotionScoreDataDTO> getEmotionScoresForMe() {
        Long memberId = currentUserService.getCurrentMemberId();

        Pageable pageable = PageRequest.of(0, 7); // 최신 7개
        List<EmotionScoreDataDTO> scores = analysisRepository.findEmotionScoresByMemberId(memberId, pageable);

        // 그래프용: 과거 → 현재
        Collections.reverse(scores);

        return scores;
    }

    // 감정분석 최신데이터 1개 가져오기
    // TODO : 구버전
    @Override
    public Optional<EmotionAnalysisResponseDTO> getLatestAnalysis(Long memberId) {
        return analysisRepository.findTopByEmotionEntry_Member_IdOrderByCreatedAtDesc(memberId)
                .map(EmotionAnalysisResponseDTO::from);
    }

    // TODO : 신버전
    @Override
    public Optional<EmotionAnalysisResponseDTO> getLatestAnalysisForMe() {
        Long memberId = currentUserService.getCurrentMemberId();

        return analysisRepository.findTopByEmotionEntry_Member_IdOrderByCreatedAtDesc(memberId)
                .map(EmotionAnalysisResponseDTO::from);
    }

}
