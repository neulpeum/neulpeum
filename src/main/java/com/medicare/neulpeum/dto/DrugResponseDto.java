package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DrugResponseDto {

    private Long drugId;
    private String drugName;
    private LocalDate expireDate;
    private int usableAmount;
    private String drugEnrollTime;
    private String drugModifiedTime;

    public DrugResponseDto(DrugInfo drugInfo) {
        this.drugId = drugInfo.getId();
        this.drugName = drugInfo.getDrugName();
        this.expireDate = drugInfo.getExpireDate();
        this.usableAmount = drugInfo.getUsableAmount();
        this.drugEnrollTime = drugInfo.getCreatedAt();
        this.drugModifiedTime = drugInfo.getModifiedAt();
    }
}
