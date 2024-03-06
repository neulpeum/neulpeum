package com.medicare.neulpeum.service;

import com.medicare.neulpeum.dto.AdminUpdateRequestDto;
import com.medicare.neulpeum.dto.UserPasswordUpdateRequestDto;

public interface UserService {
    void update(UserPasswordUpdateRequestDto userPasswordUpdateRequestDto);

    void adminUpdate(AdminUpdateRequestDto adminUpdateRequestDto);
}
