package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConsultUpdateRequestDto {
    private Long consultId;
    private String providerName;
    private String consultContent;
    private String takingDrug;

    public ConsultContentInfo toEntity(ConsultUpdateRequestDto cuReq) {
        return ConsultContentInfo.builder()
                .consultId(cuReq.getConsultId())
                .providerName(cuReq.getProviderName())
                .consultContent(cuReq.getConsultContent())
                .takingDrug(cuReq.getTakingDrug())
                .build();

    }
}
