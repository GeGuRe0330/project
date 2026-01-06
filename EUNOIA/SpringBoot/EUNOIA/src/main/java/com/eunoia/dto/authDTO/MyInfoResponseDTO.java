package com.eunoia.dto.authDTO;

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
public class MyInfoResponseDTO {
    private Long id;
    private String email;
    private String nickname;
    private String role;
    private String status;

    public static MyInfoResponseDTO from(Member member) {
        return MyInfoResponseDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .role(member.getRole().name())
                .status(member.getStatus().name())
                .build();
    }
}
