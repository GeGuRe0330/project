package com.eunoia.repository;

import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.domain.Member;
import com.eunoia.dto.emotionAnalysis.EmotionScoreDataDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
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

    @Query("""
                select a
                from EmotionAnalysis a
                join fetch a.emotionEntry e
                where e.member = :member
                  and e.entryDate between :start and :end
                  and a.entryClarityScore >= :minScore
                order by e.entryDate desc, a.entryClarityScore desc, e.id desc
            """)
    List<EmotionAnalysis> findCandidateAnalysesForMeta(
            @Param("member") Member member,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("minScore") int minScore);
}
