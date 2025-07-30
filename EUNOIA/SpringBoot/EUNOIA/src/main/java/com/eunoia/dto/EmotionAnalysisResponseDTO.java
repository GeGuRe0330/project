package com.eunoia.dto;

import com.eunoia.domain.EmotionAnalysis;
import lombok.*;

import java.time.LocalDateTime;

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

    public static EmotionAnalysisResponseDTO from(EmotionAnalysis analysis) {
        return EmotionAnalysisResponseDTO.builder()
                .id(analysis.getId())
                .emotionDetected(analysis.getEmotionDetected())
                .keywords(analysis.getKeywords())
                .insightSummary(analysis.getInsightSummary())
                .flowHint(analysis.getFlowHint())
                .createdAt(analysis.getCreatedAt())
                .build();
    }
}
