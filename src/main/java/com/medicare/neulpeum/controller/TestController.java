package com.medicare.neulpeum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/api/test")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<?> authTest(){
        return new ResponseEntity<>("인증 완료", HttpStatus.OK);
    }
}
