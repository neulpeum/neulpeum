package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.domain.entity.StoredDrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DrugResponseDto {

    private String drugName;
    private LocalDate expireDate;
    private int useableAmount;
    private LocalDate drugEnrollTime;
    private LocalDate drugModifiedTime;

    public DrugResponseDto(DrugInfo drugInfo, StoredDrugInfo storedDrugInfo) {
        this.drugName = drugInfo.getDrugName();
        this.expireDate = storedDrugInfo.getExpireDate();
        this.useableAmount = storedDrugInfo.getUsableAmount();
        this.drugEnrollTime = drugInfo.getCreatedAt();
        this.drugModifiedTime = drugInfo.getModifiedAt();
    }
}
