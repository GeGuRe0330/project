package com.eunoia.dto;

import com.eunoia.domain.Member.Gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDTO {
    private String nickname;
    private Integer age;
    private Gender gender;
}
