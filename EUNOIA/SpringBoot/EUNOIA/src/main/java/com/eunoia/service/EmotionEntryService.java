package com.eunoia.service;

import com.eunoia.dto.EmotionEntryRequestDTO;
import com.eunoia.dto.EmotionEntryResponseDTO;

import java.util.List;

public interface EmotionEntryService {
    EmotionEntryResponseDTO createEmotionEntry(Long memberId, EmotionEntryRequestDTO dto);

    EmotionEntryResponseDTO getEmotionEntryById(Long id);

    List<EmotionEntryResponseDTO> getAllEmotionEntriesByMember(Long memberId);

    EmotionEntryResponseDTO updateEmotionEntry(Long id, EmotionEntryRequestDTO dto);

    void deleteEmotionEntry(Long id);
}
