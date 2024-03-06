package com.medicare.neulpeum.controller;

import com.medicare.neulpeum.dto.AdminUpdateRequestDto;
import com.medicare.neulpeum.dto.UserPasswordUpdateRequestDto;
import com.medicare.neulpeum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //대학생(일반유저) 비밀번호 변경
    @PatchMapping("/admin/changePw")
    public ResponseEntity<?> updateUserPasswordInfo(@RequestBody UserPasswordUpdateRequestDto userPasswordUpdateRequestDto) {
        try {
            userService.update(userPasswordUpdateRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("user 비밀번호 변경 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("user 비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }


    //관리자 아이디 비밀번호 변경
    @PatchMapping("/admin")
    public ResponseEntity<?> updateAdminInfo(@RequestBody AdminUpdateRequestDto adminUpdateRequestDto) {
        try {
            userService.adminUpdate(adminUpdateRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("admin 비밀번호 변경 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("관리자 아이디 및 비밀번호 변경 중 오류 발생 " + e.getMessage());
        }
    }

}
