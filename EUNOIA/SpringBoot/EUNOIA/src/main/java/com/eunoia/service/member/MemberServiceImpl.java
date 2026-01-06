package com.eunoia.service.member;

import com.eunoia.common.error.CustomException;
import com.eunoia.common.error.ErrorCode;
import com.eunoia.domain.Member;
import com.eunoia.domain.Member.Role;
import com.eunoia.domain.Member.Status;
import com.eunoia.dto.authDTO.MemberSignupRequestDTO;
import com.eunoia.dto.authDTO.MyInfoResponseDTO;
import com.eunoia.repository.MemberRepository;
import com.eunoia.security.CurrentUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserService currentUserService;

    @Override
    public void signup(MemberSignupRequestDTO dto) {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL, "이미 사용 중인 이메일입니다.");
        }

        Member member = Member.builder()
                .email(dto.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .age(dto.getAge())
                .gender(dto.getGender())
                .role(Role.USER)
                .status(Status.PENDING)
                .build();

        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MyInfoResponseDTO getMyInfo() {
        Member me = currentUserService.getCurrentMember();
        return MyInfoResponseDTO.from(me);
    }
}
