package com.eunoia.repository;

import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.dto.EmotionScoreDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmotionAnalysisRepository extends JpaRepository<EmotionAnalysis,Long> {
    Optional<EmotionAnalysis> findByEmotionEntry(EmotionEntry entry);

    void deleteByEmotionEntry_Id(Long id);

    @Query("SELECT new com.eunoia.dto.EmotionScoreDataDTO(a.createdAt, a.emotionScore) " +
            "FROM EmotionAnalysis a " +
            "WHERE a.emotionEntry.member.id = :memberId " +
            "ORDER BY a.createdAt ASC")
    List<EmotionScoreDataDTO> findEmotionScoresByMemberId(@Param("memberId") Long memberId);

    Optional<EmotionAnalysis> findTopByEmotionEntry_Member_IdOrderByCreatedAtDesc(Long memberId);


}
