package com.medicare.neulpeum.controller;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.*;
import com.medicare.neulpeum.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    // 주민 추가
    @PostMapping("/patient")
    public ResponseEntity<?> postPatientInfo(@RequestBody PatientRequestDto patientRequestDto) {
        try {
            patientService.save(patientRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("patient info 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("patient info 저장 중 오류 발생: " + e.getMessage());
        }
    }

    // 주민 조회
    @GetMapping("/patient")
    public ResponseEntity<List<PatientResponseDto>> getPatient() {
        List<PatientResponseDto> patientResponseDto = patientService.findAll();

        return ResponseEntity.ok(patientResponseDto);
    }

    // 주민 검색
    @GetMapping("/findPatient")
    public ResponseEntity<List<PatientResponseDto>> findPatient(@RequestParam String patientName) {
        List<PatientResponseDto> patientResponseDtoList = patientService.findAllByPatientName(patientName);

        return ResponseEntity.ok(patientResponseDtoList);
    }

    // 주민 상세 정보 조회
    @GetMapping("/patientInfo")
    public ResponseEntity<PatientDetailResponseDto> getPatientDetail(@RequestParam Long patientId) {
        PatientDetailResponseDto patientDetailResponseDto = patientService.findByPatientId(patientId);
        if (patientDetailResponseDto != null) {
            return ResponseEntity.ok(patientDetailResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 주민 상세 정보 수정
    @PutMapping("/patientInfo")
    public ResponseEntity<?> updatePatientInfo(@RequestBody PatientDetailRequestDto patientDetailRequestDto) {
        try {
            patientService.update(patientDetailRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("환자 정보 수정 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("환자를 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("환자 정보 수정 중 오류 발생: " + e.getMessage());
        }
    }
}
