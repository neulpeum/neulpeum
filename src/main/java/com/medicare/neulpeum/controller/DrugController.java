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

    // 약 재고 수정 및 새로운 약에 대해 저장
    @PutMapping("/drug")
    public ResponseEntity<?> updateDrugInfo(@RequestBody List<DrugRequestDto> drugRequestDtoList) {
        try {
            for (DrugRequestDto drugRequestDto : drugRequestDtoList) {
                // 해당 약물 정보가 이미 존재하는지 확인
                if (drugService.existsByDrugId(drugRequestDto.getDrugId())) {
                    // 이미 존재하는 경우에는 업데이트 수행
                    drugService.update(drugRequestDto);
                } else {
                    // 존재하지 않는 경우에는 새로운 약물 정보를 추가
                    drugService.save(drugRequestDto);
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body("drug info 수정 및 추가 완료");
        } catch (Exception e) {
            // 예외 발생 시 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("drug info 수정 또는 추가 중 오류 발생: " + e.getMessage());
        }
    }


    // 약 재고 전체 조회
    @GetMapping("/drug")
    public ResponseEntity<List<DrugResponseDto>> getDrugInfo() {
        List<DrugResponseDto> drugResponseDtoList = drugService.findAll();

        return ResponseEntity.ok(drugResponseDtoList);
    }

    // 약 재고 검색 기능 - 미사용
//    @GetMapping("/findDrug")
//    public ResponseEntity<List<DrugResponseDto>> findDrugInfo(@RequestParam String drugName) {
//        List<DrugResponseDto> drugResponseDtoList = drugService.findByDrugName(drugName);
//
//        return ResponseEntity.ok(drugResponseDtoList);
//    }
}
