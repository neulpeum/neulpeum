package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientResponseDto {
    private String name;
    private String address;
    private String disease;
    private String takingDrug;
    private String specialReport;

    public PatientResponseDto(PatientInfo patientInfo) {
        this.name = patientInfo.getPatientName();
        this.address = patientInfo.getAddress();
        this.disease = patientInfo.getDisease();
        this.takingDrug = patientInfo.getTakingDrug();
        this.specialReport = patientInfo.getSpecialReport();
    }
}
