package com.eunoia.repository;

import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.dto.emotionAnalysis.EmotionScoreDataDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmotionAnalysisRepository extends JpaRepository<EmotionAnalysis, Long> {
    Optional<EmotionAnalysis> findByEmotionEntry(EmotionEntry entry);

    @Query("""
                SELECT new com.eunoia.dto.emotionAnalysis.EmotionScoreDataDTO(a.createdAt, a.emotionScore)
                FROM EmotionAnalysis a
                WHERE a.emotionEntry.member.id = :memberId
                ORDER BY a.createdAt DESC
            """)
    List<EmotionScoreDataDTO> findEmotionScoresByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    Optional<EmotionAnalysis> findTopByEmotionEntry_Member_IdOrderByCreatedAtDesc(Long memberId);
}
