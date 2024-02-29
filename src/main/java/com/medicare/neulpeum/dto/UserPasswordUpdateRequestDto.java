package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordUpdateRequestDto {
    private String username;
    private String password;

    public UserInfo toEntity(UserPasswordUpdateRequestDto upwREQ) {
     return UserInfo.builder()
             .username(upwREQ.getUsername())
             .password(upwREQ.getPassword())
             .build();
    }

}
