package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DrugResponseDto {

    private String drugNameKor;

    public DrugResponseDto(DrugInfo drugInfo) {
        this.drugNameKor = drugInfo.getDrugNameKor();
    }
}
