package com.eunoia.dto.meta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MetaAnalysisLatestResponseDTO {
    private MetaAnalysisStatusDTO status;

    private LocalDate periodStart;
    private LocalDate periodEnd;

    private int requiredCount;
    private int currentCount;

    private List<String> actions;

    private JsonNode resultJson;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
