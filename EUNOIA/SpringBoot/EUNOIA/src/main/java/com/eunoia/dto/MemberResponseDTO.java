package com.eunoia.dto;

import com.eunoia.domain.Member;
import com.eunoia.domain.Member.Gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDTO {
    private Long id;
    private String nickname;
    private Integer age;
    private Gender gender;

    public static MemberResponseDTO from(Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .age(member.getAge())
                .gender(member.getGender())
                .build();
    }
}
