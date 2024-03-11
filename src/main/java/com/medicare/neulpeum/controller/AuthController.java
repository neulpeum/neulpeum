package com.medicare.neulpeum.controller;

import com.medicare.neulpeum.dto.UserRequestDto;
import com.medicare.neulpeum.service.CustomUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final CustomUserDetailService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody UserRequestDto requestDto) {
        try {
            userService.login(request, response, requestDto);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 오류 발생" + e.getMessage() );
        }

    }

}