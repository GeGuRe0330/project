package com.eunoia.dto.emotionAnalysis;

import com.eunoia.domain.EmotionAnalysis;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionAnalysisResponseDTO {
    private Long id;
    private String emotionDetected;
    private String keywords;
    private String insightSummary;
    private String flowHint;
    private LocalDateTime createdAt;
    private List<String> warmMessages;
    private String emotionSummary;
    private Double emotionScore;

    public static EmotionAnalysisResponseDTO from(EmotionAnalysis analysis) {
        return EmotionAnalysisResponseDTO.builder()
                .id(analysis.getId())
                .emotionDetected(analysis.getEmotionDetected())
                .keywords(analysis.getKeywords())
                .insightSummary(analysis.getInsightSummary())
                .flowHint(analysis.getFlowHint())
                .createdAt(analysis.getCreatedAt())
                .warmMessages(analysis.getWarmMessages())
                .emotionSummary(analysis.getEmotionSummary())
                .emotionScore(analysis.getEmotionScore())
                .build();
    }
}
