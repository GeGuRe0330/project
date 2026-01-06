package com.eunoia.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eunoia.common.response.ApiResponse;
import com.eunoia.dto.admin.PendingMemberResponseDTO;
import com.eunoia.service.admin.AdminMemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/members")
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    // 승인대기 목록 조회
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<PendingMemberResponseDTO>>> pendingMembers() {
        List<PendingMemberResponseDTO> result = adminMemberService.getPendingMembers();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 승인 처리
    @PatchMapping("/{memberId}/approve")
    public ResponseEntity<ApiResponse<Void>> approve(@PathVariable Long memberId) {
        adminMemberService.approveMember(memberId);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
