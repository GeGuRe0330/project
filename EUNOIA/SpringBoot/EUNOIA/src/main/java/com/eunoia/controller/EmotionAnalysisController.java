package com.eunoia.controller;

import com.eunoia.dto.EmotionAnalysisRequestDTO;
import com.eunoia.dto.EmotionAnalysisResponseDTO;
import com.eunoia.dto.EmotionScoreDataDTO;
import com.eunoia.service.EmotionAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/analyses")
@RequiredArgsConstructor
public class EmotionAnalysisController {

    private final EmotionAnalysisService analysisService;

    // 분석 생성 (감정 글 기준)
    // @PostMapping("/{entryId}")
    // public ResponseEntity<EmotionAnalysisResponseDTO> create(
    // @PathVariable Long entryId,
    // @RequestBody EmotionAnalysisRequestDTO dto) {
    // return ResponseEntity.ok(analysisService.createAnalysis(entryId, dto));
    // }

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

    // 분석 생성 & 수정
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

    @PostMapping("/warm-messages/{entryId}")
    public ResponseEntity<EmotionAnalysisResponseDTO> createWarmMessages(
            @PathVariable Long entryId) {
        return ResponseEntity.ok(analysisService.createWarmMessages(entryId));
    }

    // 따뜻한 말 가져오기
    @GetMapping("/warm-messages/{entryId}")
    public ResponseEntity<List<String>> getWarmMessages(@PathVariable Long entryId) {
        return ResponseEntity.ok(analysisService.getWarmMessagesByEntryId(entryId));
    }

    // 감정 점수 스코어 가져오기
    // TODO : 구버전
    // @GetMapping("/scores/member/{memberId}")
    // public ResponseEntity<List<EmotionScoreDataDTO>>
    // getEmotionScores(@PathVariable Long memberId) {
    // return ResponseEntity.ok(analysisService.getEmotionScoresByMember(memberId));
    // }

    // TODO : 신버전
    @GetMapping("/me/scores")
    public ResponseEntity<List<EmotionScoreDataDTO>> getEmotionScoresForMe() {
        return ResponseEntity.ok(analysisService.getEmotionScoresForMe());
    }

    // 메인보드에서 조회되는 감정분석 1건 조회 요청
    // TODO : 로그인 기반 전환 완료 후 제거 or 재설계 예정
    @GetMapping("/member/{memberId}/latest")
    public ResponseEntity<EmotionAnalysisResponseDTO> getLatestAnalysis(@PathVariable Long memberId) {
        return analysisService.getLatestAnalysis(memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    // TODO : 분석 최신 단건 조회 로그인 버전 ( 완성 후 주석 삭제 예정 )
    @GetMapping("me/latest")
    public ResponseEntity<EmotionAnalysisResponseDTO> getLatestAnalysisForMe() {
        return analysisService.getLatestAnalysisForMe()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

}
