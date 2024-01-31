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

    // 약 재고 저장 기본 CRUD 코드 -> api 문서 형태에 맞춰 수정 필요
    @PostMapping("/drug")
    public ResponseEntity<?> postDrugInfo(@RequestBody DrugRequestDto diReq) {
        try {
            drugService.save(diReq);
            return ResponseEntity.status(HttpStatus.CREATED).body("drug info 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("drug info 저장 중 오류 발생: "+ e.getMessage());
        }
    }

    @GetMapping("/drug")
    public ResponseEntity<List<DrugResponseDto>> getDrugInfo() {
        List<DrugResponseDto> drugResponseDtoList = drugService.findAll();

        return ResponseEntity.ok(drugResponseDtoList);
    }

    @PutMapping("/drugInfo/{drugId}")
    public ResponseEntity<DrugResponseDto> updateDrugInfo(
            @PathVariable("drugId") long drugId,
            @RequestBody DrugRequestDto diReq
    ) {
        try {
            ResponseEntity.ok(drugService.update(drugId, diReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/drugInfo/{drugId}")
    public ResponseEntity<HttpStatus> deleteDrugInfo(@PathVariable("drugId") long drugId) {
        try {
            drugService.delete(drugId);
            ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
