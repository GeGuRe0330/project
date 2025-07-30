package com.eunoia.service;

import com.eunoia.dto.EmotionAnalysisRequestDTO;
import com.eunoia.dto.EmotionAnalysisResponseDTO;

import java.util.List;

public interface EmotionAnalysisService {
    EmotionAnalysisResponseDTO createAnalysis(Long entryId, EmotionAnalysisRequestDTO dto);

    EmotionAnalysisResponseDTO getAnalysisById(Long id);

    List<EmotionAnalysisResponseDTO> getAllAnalysesByEntry(Long entryId);

    EmotionAnalysisResponseDTO updateAnalysis(Long id, EmotionAnalysisRequestDTO dto);

    void deleteAnalysis(Long id);
}
