package com.eunoia.service.member;

import com.eunoia.dto.authDTO.MemberSignupRequestDTO;
import com.eunoia.dto.authDTO.MyInfoResponseDTO;

public interface MemberService {
    void signup(MemberSignupRequestDTO request);

    MyInfoResponseDTO getMyInfo();
}
