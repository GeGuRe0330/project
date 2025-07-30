package com.eunoia.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "emotion_entry_seq_gen",
        sequenceName = "emotion_entry_seq",
        initialValue = 1,
        allocationSize = 1
)
public class EmotionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emotion_entry_seq_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    private String content;

    private String emotionTag;

    private LocalDate entryDate;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if(this.entryDate == null) {
            this.entryDate = LocalDate.now();
        }
    }
}
