package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.Repository.PatientRepository;
import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConsultRequestDto {

    private PatientInfo patientId;
    private Date consultDate;
    private String providerName;
    private String takingDrug;
    private String consultContent;

    public ConsultContentInfo toEntity() {
        return ConsultContentInfo.builder()
                .patientId(patientId)
                .consultDate(consultDate)
                .providerName(providerName)
                .takingDrug(takingDrug)
                .consultContent(consultContent)
                .build();
    }
}
