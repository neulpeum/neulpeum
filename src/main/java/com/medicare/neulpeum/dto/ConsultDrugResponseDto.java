package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.domain.entity.ProvidedDrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConsultDrugResponseDto {
    private String drugName;
    private int providedAmount;

    public ConsultDrugResponseDto(ProvidedDrugInfo providedDrugInfo, DrugInfo drugInfo) {
        this.drugName = drugInfo.getDrugName();
        this.providedAmount = providedDrugInfo.getProvidedAmount();
    }
}
