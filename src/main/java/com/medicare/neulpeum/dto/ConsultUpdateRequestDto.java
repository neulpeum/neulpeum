package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConsultUpdateRequestDto {
//    private PatientInfo patientName;
    private Long consultId;
    private LocalDate consultDate;
    private String providerName;
    private String consultContent;
    private String takingDrug;

    public ConsultContentInfo toEntity(ConsultUpdateRequestDto cuReq) {
        return ConsultContentInfo.builder()
                .consultId(cuReq.getConsultId())
                .consultDate(cuReq.getConsultDate())
                .providerName(cuReq.getProviderName())
                .consultContent(cuReq.getConsultContent())
                .takingDrug(cuReq.getTakingDrug())
                .build();

    }
}
