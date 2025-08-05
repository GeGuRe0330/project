package com.eunoia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmotionScoreDataDTO {
    private LocalDateTime createdAt;
    private Double emotionScore;
}
