package com.eunoia.dto.BackupFileDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BackupFileDTO {
    private String name;
    private long size;
    private long modifiedAt;
}
