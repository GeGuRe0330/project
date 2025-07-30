package com.eunoia.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionEntryRequestDTO {
    private String content;
    private String emotionTag;
    private String entryDate;
}
