package com.eunoia.repository;

import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmotionAnalysisRepository extends JpaRepository<EmotionAnalysis,Long> {
    Optional<EmotionAnalysis> findByEmotionEntry(EmotionEntry entry);
}
