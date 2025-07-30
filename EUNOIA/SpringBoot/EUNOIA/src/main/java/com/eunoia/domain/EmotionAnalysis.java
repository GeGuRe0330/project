package com.eunoia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "emotion_analysis")
@SequenceGenerator(
        name = "emotion_analysis_seq_generator",
        sequenceName = "emotion_analysis_seq",
        allocationSize = 1,
        initialValue = 1
)
public class EmotionAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emotion_analysis_seq_generator")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_entry_id", nullable = false)
    private EmotionEntry emotionEntry;

    @Column(nullable = false)
    private String emotionDetected;

    @Column(length = 255)
    private String keywords;

    @Lob
    private String insightSummary;

    @Lob
    private String flowHint;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
