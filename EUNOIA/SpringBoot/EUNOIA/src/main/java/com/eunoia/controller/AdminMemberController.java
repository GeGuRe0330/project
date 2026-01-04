package com.eunoia.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<PendingMemberResponseDTO>> pendingMembers() {
        return ResponseEntity.ok(adminMemberService.getPendingMembers());
    }

    // 승인 처리
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        adminMemberService.approveMember(id);
        return ResponseEntity.noContent().build();
    }
}
