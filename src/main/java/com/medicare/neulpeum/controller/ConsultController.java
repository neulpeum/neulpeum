package com.medicare.neulpeum.controller;

import com.medicare.neulpeum.dto.ConsultRequestDto;
import com.medicare.neulpeum.service.ConsultService;
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
public class ConsultController {
    private final ConsultService consultService;

    //주민 상담 내용 추가
    @PostMapping("/patient/consult")
    public  ResponseEntity<?> postConsultInfo(@RequestBody ConsultRequestDto consultRequestDto) {
        try {
            consultService.save(consultRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("consult content 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("consult content 저장 중 오류 발생 " + e.getMessage());
        }
    }
}
