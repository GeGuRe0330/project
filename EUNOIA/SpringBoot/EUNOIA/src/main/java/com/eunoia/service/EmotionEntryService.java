package com.eunoia.service;

import com.eunoia.dto.EmotionEntryRequestDTO;
import com.eunoia.dto.EmotionEntryResponseDTO;

public interface EmotionEntryService {
    EmotionEntryResponseDTO createEmotionEntry(EmotionEntryRequestDTO dto);

    EmotionEntryResponseDTO getEmotionEntryById(Long id);
}
