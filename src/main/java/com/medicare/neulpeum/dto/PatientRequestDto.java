package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientRequestDto {
    private String patientName;
    private String address;
    private String disease;
    private String specialReport;

    public PatientInfo toEntity(String patientName, String address, String disease, String specialReport) {
        return PatientInfo.builder()
                .patientName(patientName)
                .address(address)
                .disease(disease)
                .specialReport(specialReport)
                .build();
    }
}
