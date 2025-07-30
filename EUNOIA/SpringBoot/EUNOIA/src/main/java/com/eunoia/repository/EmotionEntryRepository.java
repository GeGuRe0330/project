package com.eunoia.repository;

import com.eunoia.domain.EmotionEntry;
import com.eunoia.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmotionEntryRepository extends JpaRepository<EmotionEntry,Long> {
    List<EmotionEntry> findByMember(Member member);

    List<EmotionEntry> findByMemberAndEntryDateBetween(Member member, LocalDate start, LocalDate end);
}
