package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DrugRequestDto {

    private Long drugId;
    private String drugName;
    private LocalDate expireDate;
    private int stockAmount;
    private int usableAmount;
    private int usedAmount;

    public DrugInfo toEntity(DrugRequestDto drugReq) {
        return DrugInfo.builder()
                .id(drugReq.getDrugId())
                .drugName(drugReq.getDrugName())
                .expireDate(drugReq.getExpireDate())
                .stockAmount(drugReq.getStockAmount())
                .usableAmount(drugReq.getUsableAmount())
                .usedAmount(drugReq.getUsedAmount())
                .build();
    }
}
