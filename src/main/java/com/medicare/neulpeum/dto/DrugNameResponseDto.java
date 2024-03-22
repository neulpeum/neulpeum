package com.medicare.neulpeum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DrugNameResponseDto {
    private String drugName;

    public DrugNameResponseDto(String drugName) {
        this.drugName = drugName;
    }
}
