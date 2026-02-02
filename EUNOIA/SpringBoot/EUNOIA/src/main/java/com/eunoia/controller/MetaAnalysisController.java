package com.eunoia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eunoia.common.response.ApiResponse;
import com.eunoia.dto.meta.MetaAnalysisLatestResponseDTO;
import com.eunoia.service.meta.MetaAnalysisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/meta")
@RequiredArgsConstructor
public class MetaAnalysisController {

    private final MetaAnalysisService metaAnalysisService;

    // 1) 페이지 진입 시: 최신 상태/결과 조회
    @GetMapping("/me/latest")
    public ResponseEntity<ApiResponse<MetaAnalysisLatestResponseDTO>> getLatestForMe() {
        MetaAnalysisLatestResponseDTO result = metaAnalysisService.getLatestForMe();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 2) 조건 충족 시: 메타 분석 생성/갱신(UPSERT)
    @PostMapping("/me/analysis")
    public ResponseEntity<ApiResponse<MetaAnalysisLatestResponseDTO>> upsertLatestForMe() {
        MetaAnalysisLatestResponseDTO result = metaAnalysisService.upsertLatestForMe();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
