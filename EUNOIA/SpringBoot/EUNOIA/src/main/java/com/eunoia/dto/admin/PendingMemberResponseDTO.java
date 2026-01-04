package com.eunoia.dto.admin;

import java.time.LocalDateTime;

import com.eunoia.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingMemberResponseDTO {
    private Long id;
    private String email;
    private String nickname;
    private Integer age;
    private Member.Gender gender;
    private LocalDateTime createdAt;
    private Member.Status status;
    private Member.Role role;
}
