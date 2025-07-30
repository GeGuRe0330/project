package com.eunoia.service;

import com.eunoia.domain.EmotionEntry;
import com.eunoia.domain.Member;
import com.eunoia.dto.EmotionEntryRequestDTO;
import com.eunoia.dto.EmotionEntryResponseDTO;
import com.eunoia.repository.EmotionEntryRepository;
import com.eunoia.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmotionEntryServiceImpl implements EmotionEntryService {

    private final EmotionEntryRepository emotionEntryRepository;
    private final MemberRepository memberRepository;

    @Override
    public EmotionEntryResponseDTO createEmotionEntry(Long memberId, EmotionEntryRequestDTO dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. ID: " + memberId));

        EmotionEntry entry = EmotionEntry.builder()
                .member(member)
                .content(dto.getContent())
                .emotionTag(dto.getEmotionTag())
                .entryDate(dto.getEntryDate() != null ? LocalDate.parse(dto.getEntryDate()) : LocalDate.now())
                .build();

        return EmotionEntryResponseDTO.from(emotionEntryRepository.save(entry));
    }

    @Override
    public EmotionEntryResponseDTO getEmotionEntryById(Long id) {
        EmotionEntry entry = emotionEntryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("감정 기록을 찾을 수 없습니다. ID: " + id));

        return EmotionEntryResponseDTO.from(entry);
    }

    @Override
    public List<EmotionEntryResponseDTO> getAllEmotionEntriesByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. ID: " + memberId));

        return emotionEntryRepository.findByMember(member)
                .stream()
                .map(EmotionEntryResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public EmotionEntryResponseDTO updateEmotionEntry(Long id, EmotionEntryRequestDTO dto) {
        EmotionEntry entry = emotionEntryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("수정할 감정 기록이 없습니다. ID: " + id));

        entry.setContent(dto.getContent());
        entry.setEmotionTag(dto.getEmotionTag());
        entry.setEntryDate(dto.getEntryDate() != null ? LocalDate.parse(dto.getEntryDate()) : entry.getEntryDate());

        return EmotionEntryResponseDTO.from(emotionEntryRepository.save(entry));
    }

    @Override
    public void deleteEmotionEntry(Long id) {
        if (!emotionEntryRepository.existsById(id)) {
            throw new EntityNotFoundException("삭제할 감정 기록이 없습니다. ID: " + id);
        }
        emotionEntryRepository.deleteById(id);
    }
}
