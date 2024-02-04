package com.medicare.neulpeum.controller;

import com.medicare.neulpeum.dto.PatientRequestDto;
import com.medicare.neulpeum.service.PatientService;
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
public class PatientController {
    private final PatientService patientService;

    // 주민 추가
    @PostMapping("/patient")
    public ResponseEntity<?> patientInfo(@RequestBody PatientRequestDto patientRequestDto) {
        try {
            patientService.save(patientRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("patient info 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("patient info 저장 중 오류 발생: " + e.getMessage());
        }
    }
}
