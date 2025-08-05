package com.eunoia.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionAnalysisRequestDTO {
    private Long emotionEntryId;     // 분석 대상 감정 글 ID
    private String emotionDetected;  // 감정 대표값 (예: 분노, 기대)
    private String keywords;         // 감정 키워드 요약 (쉼표 구분)
    private String insightSummary;   // 간단한 감정 분석 요약
    private String flowHint;         // 흐름 연결 힌트
    private String emotionSummary;
    private Double emotionScore;
    private List<String> warmMessages;
}
