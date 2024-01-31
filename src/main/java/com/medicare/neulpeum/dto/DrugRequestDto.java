package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DrugRequestDto {

    private String drugName;

    public DrugInfo toEntity(String drugName) {
        return DrugInfo.builder()
                .drugName(drugName)
                .build();
    }
}
