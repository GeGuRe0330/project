package com.eunoia.dto;

import com.eunoia.domain.EmotionEntry;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionEntryResponseDTO {
    private Long id;
    private String content;
    private String emotionTag;
    private LocalDate entryDate;
    private LocalDateTime createdAt;

    public static EmotionEntryResponseDTO from(EmotionEntry entry) {
        return EmotionEntryResponseDTO.builder()
                .id(entry.getId())
                .content(entry.getContent())
                .emotionTag(entry.getEmotionTag())
                .entryDate(entry.getEntryDate())
                .createdAt(entry.getCreatedAt())
                .build();
    }
}
