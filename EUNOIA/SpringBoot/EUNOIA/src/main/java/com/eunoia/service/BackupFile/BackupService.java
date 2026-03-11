package com.eunoia.service.BackupFile;

import com.eunoia.dto.BackupFileDTO.BackupFileDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class BackupService {

    @Value("${app.backup-dir}")
    private String backupDir;

    // 파일 목록 조회
    public List<BackupFileDTO> getBackupFiles() {

        File dir = new File(backupDir);

        File[] files = dir.listFiles();

        if (files == null) {
            return List.of();
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .map(file -> new BackupFileDTO(
                        file.getName(),
                        file.length(),
                        file.lastModified()))
                .toList();
    }

    // 파일 다운로드
    public Resource download(String filename) throws MalformedURLException {

        // 간단한 보안 체크
        if (filename.contains("..") || filename.contains("/") || filename.contains("\\")) {
            throw new IllegalArgumentException("Invalid filename");
        }

        Path path = Paths.get(backupDir).resolve(filename).normalize();

        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            throw new RuntimeException("File not found");
        }

        return resource;
    }
}