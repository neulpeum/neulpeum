package com.medicare.neulpeum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DrugNameAndAmountResponseDto {
    private String drugName;
    private Long totalUsableAmount;

    public DrugNameAndAmountResponseDto(String drugName, Long totalUsableAmount) {
        this.drugName = drugName;
        this.totalUsableAmount = totalUsableAmount;
    }

}
