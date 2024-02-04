package com.medicare.neulpeum.controller;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.DrugResponseDto;
import com.medicare.neulpeum.dto.PatientDetailResponseDto;
import com.medicare.neulpeum.dto.PatientRequestDto;
import com.medicare.neulpeum.dto.PatientResponseDto;
import com.medicare.neulpeum.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<PatientDetailResponseDto>> findPatientInfo(@RequestParam Long patientId) {
        List<PatientDetailResponseDto> patientDetailResponseDtoList = patientService.findByPatientId(patientId);

        return ResponseEntity.ok(patientDetailResponseDtoList);
    }
}
