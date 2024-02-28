package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PatientDetailResponseDto {
    private Long patientId;
    private String patientName;
    private String birthDate;
    private String phoneNum;
    private String address;
    private String disease;
    private String takingDrug;
    private String specialReport;
    private LocalDate patientEnrollDate;
    private LocalDate patientModifyDate;

    public PatientDetailResponseDto(PatientInfo patientInfo) {
        this.patientId = patientInfo.getId();
        this.patientName = patientInfo.getPatientName();
        this.birthDate = patientInfo.getBirthDate();
        this.phoneNum = patientInfo.getPhoneNum();
        this.address = patientInfo.getAddress();
        this.disease = patientInfo.getDisease();
        this.takingDrug = patientInfo.getTakingDrug();
        this.specialReport = patientInfo.getSpecialReport();
        this.patientEnrollDate = patientInfo.getCreatedAt();
        this.patientModifyDate = patientInfo.getModifiedAt();
    }
}
