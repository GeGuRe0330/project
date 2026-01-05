package com.eunoia.service.emotionEntry;

import com.eunoia.dto.emotionEntry.EmotionEntryRequestDTO;
import com.eunoia.dto.emotionEntry.EmotionEntryResponseDTO;

public interface EmotionEntryService {
    EmotionEntryResponseDTO createEmotionEntry(EmotionEntryRequestDTO dto);

    EmotionEntryResponseDTO getEmotionEntryById(Long id);
}
