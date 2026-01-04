package com.eunoia.controller;

import com.eunoia.common.response.ApiResponse;
import com.eunoia.dto.EmotionEntryRequestDTO;
import com.eunoia.dto.EmotionEntryResponseDTO;
import com.eunoia.service.EmotionEntryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emotion-entries")
@RequiredArgsConstructor
public class EmotionEntryController {

    private final EmotionEntryService entryService;

    // 감정 글 등록
    @PostMapping
    public ResponseEntity<ApiResponse<EmotionEntryResponseDTO>> createEntry(
            @Valid @RequestBody EmotionEntryRequestDTO dto) {
        EmotionEntryResponseDTO result = entryService.createEmotionEntry(dto);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 감정 글 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<EmotionEntryResponseDTO> getEntry(@PathVariable Long id) {
        return ResponseEntity.ok(entryService.getEmotionEntryById(id));
    }

    // 특정 회원의 감정 글 전체 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<EmotionEntryResponseDTO>> getEntriesByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(entryService.getAllEmotionEntriesByMember(memberId));
    }

    // 감정 글 수정
    @PutMapping("/{id}")
    public ResponseEntity<EmotionEntryResponseDTO> updateEntry(
            @PathVariable Long id,
            @RequestBody EmotionEntryRequestDTO dto) {
        return ResponseEntity.ok(entryService.updateEmotionEntry(id, dto));
    }

    // 감정 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        entryService.deleteEmotionEntry(id);
        return ResponseEntity.noContent().build();
    }
}
