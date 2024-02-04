package com.medicare.neulpeum.controller;

import com.medicare.neulpeum.dto.DrugRequestDto;
import com.medicare.neulpeum.dto.DrugResponseDto;
import com.medicare.neulpeum.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DrugController {
    private final DrugService drugService;

    // 약 재고 수정 및 새로운 약에 대해 저장 -> 수정 필요(리스트 형태로 넘어옴)
    @PostMapping("/drug")
    public ResponseEntity<?> postDrugInfo(@RequestBody DrugRequestDto diReq) {
        try {
            drugService.save(diReq);
            return ResponseEntity.status(HttpStatus.CREATED).body("drug info 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("drug info 저장 중 오류 발생: "+ e.getMessage());
        }
    }

    // 약 재고 전체 조회
    @GetMapping("/drug")
    public ResponseEntity<List<DrugResponseDto>> getDrugInfo() {
        List<DrugResponseDto> drugResponseDtoList = drugService.findAll();

        return ResponseEntity.ok(drugResponseDtoList);
    }

    // 약 재고 검색 기능
    @GetMapping("/findDrug")
    public ResponseEntity<List<DrugResponseDto>> findDrugInfo(@RequestParam String drugName) {
        List<DrugResponseDto> drugResponseDtoList = drugService.findByDrugName(drugName);

        return ResponseEntity.ok(drugResponseDtoList);
    }
}
