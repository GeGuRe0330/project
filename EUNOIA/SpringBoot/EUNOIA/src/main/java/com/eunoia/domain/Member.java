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
@SequenceGenerator(
        name = "member_seq_gen",
        sequenceName = "member_seq",
        initialValue = 1,
        allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    private Integer age;

    private String gender;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
