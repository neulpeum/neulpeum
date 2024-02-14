package com.medicare.neulpeum.dto;


import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConsultRequestDto {
//    PatientInfo patientInfo;

    private PatientInfo patientId;
    private PatientInfo patientName;
    private String providerName;
    private String takingDrug;
    private String consultContent;


    public ConsultContentInfo toEntity(PatientInfo patientInfo, PatientInfo patientName, String providerName, String takingDrug, String consultContent) {
        return ConsultContentInfo.builder()
                .patientId(patientInfo)
                .patientName(patientInfo)
                .providerName(providerName)
                .takingDrug(takingDrug)
                .consultContent(consultContent)
                .build();
    }

}
