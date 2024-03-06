package com.medicare.neulpeum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminUpdateRequestDto {
    private String currentPassword;
    private String newPassword;

}
