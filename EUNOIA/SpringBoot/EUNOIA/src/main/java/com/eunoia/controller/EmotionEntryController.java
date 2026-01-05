package com.eunoia.controller;

import com.eunoia.common.response.ApiResponse;
import com.eunoia.dto.EmotionEntryRequestDTO;
import com.eunoia.dto.EmotionEntryResponseDTO;
import com.eunoia.service.EmotionEntryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<EmotionEntryResponseDTO>> getEntry(
            @PathVariable Long id) {
        EmotionEntryResponseDTO result = entryService.getEmotionEntryById(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
