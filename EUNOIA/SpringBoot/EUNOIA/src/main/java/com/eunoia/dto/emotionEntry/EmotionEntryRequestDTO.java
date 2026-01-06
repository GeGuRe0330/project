package com.eunoia.dto.emotionEntry;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionEntryRequestDTO {
    @NotBlank(message = "감정글은 필수입니다.")
    private String content;
    private String emotionTag;
    private String entryDate;
}
