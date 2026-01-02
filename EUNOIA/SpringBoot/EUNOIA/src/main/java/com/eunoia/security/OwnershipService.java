package com.eunoia.security;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eunoia.domain.EmotionEntry;
import com.eunoia.repository.EmotionEntryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnershipService {

    private final CurrentUserService currentUserService;
    private final EmotionEntryRepository emotionEntryRepository;

    public EmotionEntry validateEntryOwnership(Long entryId) {
        EmotionEntry entry = emotionEntryRepository.findById(entryId)
                .orElseThrow(() -> new EntityNotFoundException("감정글을 찾을 수 없습니다. ID: " + entryId));

        Long currentMemberId = currentUserService.getCurrentMemberId();
        Long ownerId = entry.getMember().getId();

        if (!ownerId.equals(currentMemberId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 감정글에 접근 권한이 없습니다.");
        }

        return entry;
    }

}
