package com.eunoia.service.emotionAnalysis;

import java.util.List;

import com.eunoia.dto.emotionAnalysis.EmotionAnalysisResponseDTO;
import com.eunoia.dto.emotionAnalysis.EmotionScoreDataDTO;

public interface EmotionAnalysisService {

    EmotionAnalysisResponseDTO getLatestAnalysisForMe();

    EmotionAnalysisResponseDTO updateAnalysis(Long id);

    EmotionAnalysisResponseDTO createWarmMessages(Long entryId);

    List<String> getWarmMessagesByEntryId(Long entryId);

    List<EmotionScoreDataDTO> getEmotionScoresForMe();

}
