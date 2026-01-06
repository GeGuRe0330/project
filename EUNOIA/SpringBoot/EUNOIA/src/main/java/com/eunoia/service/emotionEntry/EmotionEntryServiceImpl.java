package com.eunoia.service.emotionEntry;

import com.eunoia.domain.EmotionEntry;
import com.eunoia.domain.Member;
import com.eunoia.dto.emotionEntry.EmotionEntryRequestDTO;
import com.eunoia.dto.emotionEntry.EmotionEntryResponseDTO;
import com.eunoia.repository.EmotionEntryRepository;
import com.eunoia.security.CurrentUserService;
import com.eunoia.security.OwnershipService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmotionEntryServiceImpl implements EmotionEntryService {

        private final EmotionEntryRepository emotionEntryRepository;
        private final CurrentUserService currentUserService;
        private final OwnershipService ownershipService;

        @Override
        @Transactional
        public EmotionEntryResponseDTO createEmotionEntry(EmotionEntryRequestDTO dto) {
                Member member = currentUserService.getCurrentMember();

                EmotionEntry entry = EmotionEntry.builder()
                                .member(member)
                                .content(dto.getContent())
                                .emotionTag(dto.getEmotionTag())
                                .entryDate(dto.getEntryDate() != null ? LocalDate.parse(dto.getEntryDate())
                                                : LocalDate.now())
                                .build();

                return EmotionEntryResponseDTO.from(emotionEntryRepository.save(entry));
        }

        @Override
        @Transactional(readOnly = true)
        public EmotionEntryResponseDTO getEmotionEntryById(Long id) {
                EmotionEntry entry = ownershipService.validateEntryOwnership(id);
                return EmotionEntryResponseDTO.from(entry);
        }
}
