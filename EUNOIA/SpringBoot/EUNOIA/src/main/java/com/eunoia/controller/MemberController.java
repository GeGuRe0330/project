package com.eunoia.controller;

import com.eunoia.common.response.ApiResponse;
import com.eunoia.dto.authDTO.MemberSignupRequestDTO;
import com.eunoia.dto.authDTO.MyInfoResponseDTO;
import com.eunoia.service.member.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 이메일로 사용자의 정보를 세션에서 인증 후 받아오기
    @GetMapping("/user/me")
    public ResponseEntity<ApiResponse<MyInfoResponseDTO>> me() {
        MyInfoResponseDTO result = memberService.getMyInfo();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@Valid @RequestBody MemberSignupRequestDTO dto) {
        memberService.signup(dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
