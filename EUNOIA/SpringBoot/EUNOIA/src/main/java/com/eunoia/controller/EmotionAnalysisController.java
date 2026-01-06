package com.eunoia.controller;

import com.eunoia.common.response.ApiResponse;
import com.eunoia.dto.emotionAnalysis.EmotionAnalysisResponseDTO;
import com.eunoia.dto.emotionAnalysis.EmotionScoreDataDTO;
import com.eunoia.service.emotionAnalysis.EmotionAnalysisService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyses")
@RequiredArgsConstructor
public class EmotionAnalysisController {

    private final EmotionAnalysisService analysisService;

    // 로그인한 사용자의 최신 분석 가져오기
    @GetMapping("me/latest")
    public ResponseEntity<ApiResponse<EmotionAnalysisResponseDTO>> getLatestAnalysisForMe() {
        EmotionAnalysisResponseDTO result = analysisService.getLatestAnalysisForMe();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 분석 생성 & 수정
    @PutMapping("/{entryId}")
    public ResponseEntity<ApiResponse<EmotionAnalysisResponseDTO>> update(
            @PathVariable Long entryId) {
        EmotionAnalysisResponseDTO result = analysisService.updateAnalysis(entryId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 따뜻한 말 생성
    @PostMapping("/warm-messages/{entryId}")
    public ResponseEntity<ApiResponse<EmotionAnalysisResponseDTO>> createWarmMessages(
            @PathVariable Long entryId) {
        EmotionAnalysisResponseDTO result = analysisService.createWarmMessages(entryId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 따뜻한 말 가져오기
    @GetMapping("/warm-messages/{entryId}")
    public ResponseEntity<ApiResponse<List<String>>> getWarmMessages(@PathVariable Long entryId) {
        List<String> result = analysisService.getWarmMessagesByEntryId(entryId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 사용자의 감정 점수 가져오기
    @GetMapping("/me/scores")
    public ResponseEntity<ApiResponse<List<EmotionScoreDataDTO>>> getEmotionScoresForMe() {
        List<EmotionScoreDataDTO> result = analysisService.getEmotionScoresForMe();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
