package com.eunoia.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.eunoia.common.error.CustomException;
import com.eunoia.common.error.ErrorCode;
import com.eunoia.domain.Member;
import com.eunoia.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new CustomException(ErrorCode.AUTH_REQUIRED);
        }

        String email = auth.getName();

        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
    }

    public Long getCurrentMemberId() {
        return getCurrentMember().getId();
    }
}
