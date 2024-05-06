package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DrugUpdateRequestDto {
    private ConsultContentInfo consultId;
    private String drugName;
    private int usedAmount;

}
