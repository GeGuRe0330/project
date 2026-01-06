package com.eunoia.security;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunoia.common.error.CustomException;
import com.eunoia.common.error.ErrorCode;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.repository.EmotionEntryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnershipService {

    private final CurrentUserService currentUserService;
    private final EmotionEntryRepository emotionEntryRepository;

    public EmotionEntry validateEntryOwnership(Long entryId) {
        EmotionEntry entry = emotionEntryRepository.findById(entryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "해당 감정글을 찾을 수 없습니다."));

        Long currentMemberId = currentUserService.getCurrentMemberId();
        Long ownerId = entry.getMember().getId();

        if (!ownerId.equals(currentMemberId)) {
            throw new CustomException(ErrorCode.FORBIDDEN_RESOURCE, "해당글에 대한 소유자가 아닙니다.");
        }

        return entry;
    }

}
