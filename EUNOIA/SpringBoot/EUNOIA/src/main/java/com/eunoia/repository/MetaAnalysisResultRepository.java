package com.eunoia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eunoia.domain.Member;
import com.eunoia.domain.MetaAnalysisResult;

public interface MetaAnalysisResultRepository extends JpaRepository<MetaAnalysisResult, Long> {
  Optional<MetaAnalysisResult> findByMember(Member member);
}
