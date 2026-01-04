package com.eunoia.dto.authDTO;

import com.eunoia.domain.Member.Gender;

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
public class MemberSignupRequestDTO {
    private String email;
    private String password;
    private String nickname;
    private Integer age;
    private Gender gender;
}
