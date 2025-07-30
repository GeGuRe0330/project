package com.eunoia.service;

import com.eunoia.domain.EmotionAnalysis;
import com.eunoia.domain.EmotionEntry;
import com.eunoia.dto.EmotionAnalysisRequestDTO;
import com.eunoia.dto.EmotionAnalysisResponseDTO;
import com.eunoia.repository.EmotionAnalysisRepository;
import com.eunoia.repository.EmotionEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmotionAnalysisServiceImpl implements EmotionAnalysisService {

    private final EmotionAnalysisRepository analysisRepository;
    private final EmotionEntryRepository entryRepository;

    @Override
    public EmotionAnalysisResponseDTO createAnalysis(Long entryId, EmotionAnalysisRequestDTO dto) {
        EmotionEntry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new EntityNotFoundException("분석 대상 감정 글이 존재하지 않습니다. ID: " + entryId));

        EmotionAnalysis analysis = EmotionAnalysis.builder()
                .emotionEntry(entry)
                .emotionDetected(dto.getEmotionDetected())
                .keywords(dto.getKeywords())
                .insightSummary(dto.getInsightSummary())
                .flowHint(dto.getFlowHint())
                .build();

        return EmotionAnalysisResponseDTO.from(analysisRepository.save(analysis));
    }

    @Override
    public EmotionAnalysisResponseDTO getAnalysisById(Long id) {
        EmotionAnalysis analysis = analysisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("감정 분석 결과가 없습니다. ID: " + id));
        return EmotionAnalysisResponseDTO.from(analysis);
    }

    @Override
    public List<EmotionAnalysisResponseDTO> getAllAnalysesByEntry(Long entryId) {
        EmotionEntry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new EntityNotFoundException("감정 글을 찾을 수 없습니다. ID: " + entryId));

        return analysisRepository.findByEmotionEntry(entry)
                .stream()
                .map(EmotionAnalysisResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public EmotionAnalysisResponseDTO updateAnalysis(Long id, EmotionAnalysisRequestDTO dto) {
        EmotionAnalysis analysis = analysisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("감정 분석을 찾을 수 없습니다. ID: " + id));

        analysis.setEmotionDetected(dto.getEmotionDetected());
        analysis.setKeywords(dto.getKeywords());
        analysis.setInsightSummary(dto.getInsightSummary());
        analysis.setFlowHint(dto.getFlowHint());

        return EmotionAnalysisResponseDTO.from(analysisRepository.save(analysis));
    }

    @Override
    public void deleteAnalysis(Long id) {
        if (!analysisRepository.existsById(id)) {
            throw new EntityNotFoundException("삭제할 분석 결과가 없습니다. ID: " + id);
        }
        analysisRepository.deleteById(id);
    }
}
