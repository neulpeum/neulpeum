package com.medicare.neulpeum.dto;


import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.DrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProvidedDrugRequestDto {
    private ConsultContentInfo consultId;
    private DrugInfo drugId;
    private Long providedAmount;
}
