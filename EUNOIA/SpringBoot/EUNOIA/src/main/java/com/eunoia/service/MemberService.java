package com.eunoia.service;

import com.eunoia.dto.MemberRequestDTO;
import com.eunoia.dto.MemberResponseDTO;
import com.eunoia.dto.authDTO.MemberSignupRequestDTO;
import com.eunoia.dto.authDTO.MyInfoResponseDTO;

import java.util.List;

public interface MemberService {
    MemberResponseDTO createMember(MemberRequestDTO dto);

    MemberResponseDTO getMemberById(Long id);

    List<MemberResponseDTO> getAllMembers();

    MemberResponseDTO updateMember(Long id, MemberRequestDTO dto);

    void deleteMember(Long id);

    void signup(MemberSignupRequestDTO request);

    MyInfoResponseDTO getMyInfo();
}
