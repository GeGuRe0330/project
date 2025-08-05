package com.eunoia.service;

import com.eunoia.dto.EmotionAnalysisRequestDTO;
import com.eunoia.dto.EmotionAnalysisResponseDTO;
import com.eunoia.dto.EmotionScoreDataDTO;

import java.util.List;
import java.util.Optional;

public interface EmotionAnalysisService {
//    EmotionAnalysisResponseDTO createAnalysis(Long entryId, EmotionAnalysisRequestDTO dto);

    EmotionAnalysisResponseDTO getAnalysisById(Long id);

    List<EmotionAnalysisResponseDTO> getAllAnalysesByEntry(Long entryId);

    EmotionAnalysisResponseDTO updateAnalysis(Long id, EmotionAnalysisRequestDTO dto);

    void deleteAnalysis(Long id);

    EmotionAnalysisResponseDTO createWarmMessages(Long entryId);

    List<String> getWarmMessagesByEntryId(Long entryId);

    List<EmotionScoreDataDTO> getEmotionScoresByMember(Long memberId);

    Optional<EmotionAnalysisResponseDTO> getLatestAnalysis(Long memberId);


}
