package com.eunoia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "emotion_analysis")
@SequenceGenerator(name = "emotion_analysis_seq_generator", sequenceName = "emotion_analysis_seq", allocationSize = 1, initialValue = 1)
public class EmotionAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emotion_analysis_seq_generator")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_entry_id", nullable = false)
    private EmotionEntry emotionEntry;

    @Column(nullable = true)
    private String emotionDetected;

    @Column(length = 255)
    private String keywords;

    @Lob
    private String insightSummary;

    @Lob
    private String flowHint;

    @ElementCollection
    @CollectionTable(name = "analysis_warm_message", joinColumns = @JoinColumn(name = "analysis_id"))
    @Column(name = "warm_message")
    private List<String> warmMessages = new ArrayList<>();

    @Lob
    @Column(name = "emotion_summary")
    private String emotionSummary;

    @Column(name = "emotion_score")
    private Double emotionScore;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Column(name = "entry_clarity_score")
    private Integer entryClarityScore;

    @Column(name = "entry_clarity_reason", length = 4000)
    private String entryClarityReason;
}
