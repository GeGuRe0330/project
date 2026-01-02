package com.eunoia.service;

import com.eunoia.dto.EmotionAnalysisRequestDTO;
import com.eunoia.dto.EmotionAnalysisResponseDTO;
import com.eunoia.dto.EmotionScoreDataDTO;

import java.util.List;
import java.util.Optional;

public interface EmotionAnalysisService {
    // EmotionAnalysisResponseDTO createAnalysis(Long entryId,
    // EmotionAnalysisRequestDTO dto);

    EmotionAnalysisResponseDTO getAnalysisById(Long id);

    List<EmotionAnalysisResponseDTO> getAllAnalysesByEntry(Long entryId);

    EmotionAnalysisResponseDTO updateAnalysis(Long id, EmotionAnalysisRequestDTO dto);

    void deleteAnalysis(Long id);

    EmotionAnalysisResponseDTO createWarmMessages(Long entryId);

    List<String> getWarmMessagesByEntryId(Long entryId);

    // 감정 점수 가져오기 (그래프)
    // TODO : 구버전
    // List<EmotionScoreDataDTO> getEmotionScoresByMember(Long memberId);

    // TODO : 신버전
    List<EmotionScoreDataDTO> getEmotionScoresForMe();

    // 감정분석 최신데이터 1개 가져오기
    // TODO : 구버전
    Optional<EmotionAnalysisResponseDTO> getLatestAnalysis(Long memberId);

    // TODO : 신버전
    Optional<EmotionAnalysisResponseDTO> getLatestAnalysisForMe();
}
