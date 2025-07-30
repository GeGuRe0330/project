package com.eunoia.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDTO {
    private String nickname;
    private Integer age;
    private String gender;
}
