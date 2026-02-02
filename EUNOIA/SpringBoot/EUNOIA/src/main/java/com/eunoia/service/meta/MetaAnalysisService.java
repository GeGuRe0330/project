package com.eunoia.service.meta;

import com.eunoia.dto.meta.MetaAnalysisLatestResponseDTO;

public interface MetaAnalysisService {
    MetaAnalysisLatestResponseDTO getLatestForMe();

    MetaAnalysisLatestResponseDTO upsertLatestForMe();
}
