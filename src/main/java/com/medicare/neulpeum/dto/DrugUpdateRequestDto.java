package com.medicare.neulpeum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DrugUpdateRequestDto {
    private Long consultId;
    private String drugName;
    private int usedAmount;

}
