package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PatientResponseDto {
    private Long patientId;
    private String patientName;
    private String address;
    private String disease;
    private String specialReport;

    public PatientResponseDto(PatientInfo patientInfo) {
        this.patientId = patientInfo.getId();
        this.patientName = patientInfo.getPatientName();
        this.address = patientInfo.getAddress();
        this.disease = patientInfo.getDisease();
        this.specialReport = patientInfo.getSpecialReport();
    }
}
