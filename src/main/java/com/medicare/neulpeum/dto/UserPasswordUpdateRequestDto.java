package com.medicare.neulpeum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordUpdateRequestDto {
//    private String username;

    //비밀번호 변경에 필요한 request 객체
    private String currentPassword;
    private String newPassword;
}
