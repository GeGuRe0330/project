package com.eunoia.controller;

import com.eunoia.dto.MemberRequestDTO;
import com.eunoia.dto.MemberResponseDTO;
import com.eunoia.dto.authDTO.MemberSignupRequestDTO;
import com.eunoia.dto.authDTO.MyInfoResponseDTO;
import com.eunoia.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 생성
    @PostMapping
    public ResponseEntity<MemberResponseDTO> create(@RequestBody MemberRequestDTO dto) {
        return ResponseEntity.ok(memberService.createMember(dto));
    }

    // 회원 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    // 회원 전체 조회
    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAll() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> update(@PathVariable Long id, @RequestBody MemberRequestDTO dto) {
        return ResponseEntity.ok(memberService.updateMember(id, dto));
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    // 이메일로 사용자의 정보를 세션에서 인증 후 받아오기
    @GetMapping("/user/me")
    public MyInfoResponseDTO me() {
        return memberService.getMyInfo();
    }

    // 회원가입(신형)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberSignupRequestDTO dto) {
        memberService.signup(dto);
        return ResponseEntity.ok().build();
    }

}
