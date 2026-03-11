package com.eunoia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eunoia.dto.BackupFileDTO.BackupFileDTO;
import com.eunoia.service.BackupFile.BackupService;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/api/share/backups")
@RequiredArgsConstructor
public class BackupController {

    private final BackupService backupService;

    // 파일 목록 조회
    @GetMapping
    public ResponseEntity<List<BackupFileDTO>> list() {

        return ResponseEntity.ok(
                backupService.getBackupFiles());
    }

    // 다운로드
    @GetMapping("/download")
    public ResponseEntity<Resource> download(
            @RequestParam String name) throws MalformedURLException {

        Resource resource = backupService.download(name);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + name + "\"")
                .body(resource);
    }
}