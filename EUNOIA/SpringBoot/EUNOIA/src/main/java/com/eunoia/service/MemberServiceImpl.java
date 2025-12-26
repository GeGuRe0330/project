package com.eunoia.service;

import com.eunoia.domain.Member;
import com.eunoia.dto.MemberRequestDTO;
import com.eunoia.dto.MemberResponseDTO;
import com.eunoia.dto.authDTO.MemberSignupRequestDTO;
import com.eunoia.dto.authDTO.MyInfoResponseDTO;
import com.eunoia.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
    public Long signup(MemberSignupRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public MyInfoResponseDTO getMyInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMyInfo'");
    }
}
