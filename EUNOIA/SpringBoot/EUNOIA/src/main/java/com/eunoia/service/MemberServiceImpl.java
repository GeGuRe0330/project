package com.eunoia.service;

import com.eunoia.domain.Member;
import com.eunoia.domain.Member.Role;
import com.eunoia.domain.Member.Status;
import com.eunoia.dto.MemberRequestDTO;
import com.eunoia.dto.MemberResponseDTO;
import com.eunoia.dto.authDTO.MemberSignupRequestDTO;
import com.eunoia.dto.authDTO.MyInfoResponseDTO;
import com.eunoia.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDTO createMember(MemberRequestDTO dto) {
        Member member = Member.builder()
                .nickname(dto.getNickname())
                .age(dto.getAge())
                .gender(dto.getGender())
                .build();

        return MemberResponseDTO.from(memberRepository.save(member));
    }

    @Override
    public MemberResponseDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 회원이 존재하지 않습니다. ID: " + id));

        return MemberResponseDTO.from(member);
    }

    @Override
    public List<MemberResponseDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponseDTO updateMember(Long id, MemberRequestDTO dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("수정할 회원이 존재하지 않습니다. ID: " + id));

        member.setNickname(dto.getNickname());
        member.setAge(dto.getAge());
        member.setGender(dto.getGender());

        return MemberResponseDTO.from(memberRepository.save(member));
    }

    @Override
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("삭제할 회원이 존재하지 않습니다. ID: " + id);
        }
        memberRepository.deleteById(id);
    }

    @Override
    public void signup(MemberSignupRequestDTO dto) {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
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
    public MyInfoResponseDTO getMyInfo() {
        Member me = getCurrentMember();
        return MyInfoResponseDTO.from(me);
    }

    private Member getCurrentMember() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        // CustomUserDetails 쓰면 auth.getName() == email
        String email = auth.getName();

        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("로그인 사용자 정보를 찾을 수 없습니다."));
    }
}
