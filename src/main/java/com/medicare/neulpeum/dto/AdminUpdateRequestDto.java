package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminUpdateRequestDto {
    private String username;
    private String password;

    public UserInfo toEntity(AdminUpdateRequestDto adminReq) {
        return UserInfo.builder()
                .username(adminReq.getUsername())
                .password(adminReq.getPassword())
                .build();
    }
}
