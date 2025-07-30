package com.eunoia.controller;

import com.eunoia.dto.EmotionAnalysisRequestDTO;
import com.eunoia.dto.EmotionAnalysisResponseDTO;
import com.eunoia.service.EmotionAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyses")
@RequiredArgsConstructor
public class EmotionAnalysisController {

    private final EmotionAnalysisService analysisService;

    // 분석 생성 (감정 글 기준)
    @PostMapping("/{entryId}")
    public ResponseEntity<EmotionAnalysisResponseDTO> create(
            @PathVariable Long entryId,
            @RequestBody EmotionAnalysisRequestDTO dto) {
        return ResponseEntity.ok(analysisService.createAnalysis(entryId, dto));
    }

    // 분석 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<EmotionAnalysisResponseDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(analysisService.getAnalysisById(id));
    }

    // 특정 감정 글의 모든 분석 조회
    @GetMapping("/entry/{entryId}")
    public ResponseEntity<List<EmotionAnalysisResponseDTO>> getByEntry(@PathVariable Long entryId) {
        return ResponseEntity.ok(analysisService.getAllAnalysesByEntry(entryId));
    }

    // 분석 수정
    @PutMapping("/{id}")
    public ResponseEntity<EmotionAnalysisResponseDTO> update(
            @PathVariable Long id,
            @RequestBody EmotionAnalysisRequestDTO dto) {
        return ResponseEntity.ok(analysisService.updateAnalysis(id, dto));
    }

    // 분석 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        analysisService.deleteAnalysis(id);
        return ResponseEntity.noContent().build();
    }
}
