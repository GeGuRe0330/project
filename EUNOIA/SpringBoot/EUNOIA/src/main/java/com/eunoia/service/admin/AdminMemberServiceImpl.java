package com.eunoia.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunoia.common.error.CustomException;
import com.eunoia.common.error.ErrorCode;
import com.eunoia.domain.Member;
import com.eunoia.dto.admin.PendingMemberResponseDTO;
import com.eunoia.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMemberServiceImpl implements AdminMemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PendingMemberResponseDTO> getPendingMembers() {
        return memberRepository.findByStatus(Member.Status.PENDING).stream()
                .map(m -> PendingMemberResponseDTO.builder()
                        .id(m.getId())
                        .email(m.getEmail())
                        .nickname(m.getNickname())
                        .age(m.getAge())
                        .gender(m.getGender())
                        .createdAt(m.getCreatedAt())
                        .status(m.getStatus())
                        .role(m.getRole())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public void approveMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "존재하지 않는 회원입니다."));

        if (member.getStatus() == Member.Status.ACTIVE) {
            throw new CustomException(ErrorCode.INVALID_REQUEST, "이미 승인된 회원입니다.");
        }

        member.setStatus(Member.Status.ACTIVE);

    }

}
