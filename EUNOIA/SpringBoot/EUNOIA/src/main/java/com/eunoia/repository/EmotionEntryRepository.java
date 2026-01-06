package com.eunoia.repository;

import com.eunoia.domain.EmotionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionEntryRepository extends JpaRepository<EmotionEntry, Long> {
}
