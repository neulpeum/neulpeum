package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DrugRequestDto {

    private String drugNameKor;

    public DrugInfo toEntity(String drugNameKor) {
        return DrugInfo.builder()
                .drugNameKor(drugNameKor)
                .build();
    }
}
