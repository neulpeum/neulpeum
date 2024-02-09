package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConsultDetailResponseDto {
    private String patientName;
    private Date consultDate;
    private String consultContent;
    private String takingDrug;

    public ConsultDetailResponseDto(ConsultContentInfo consultContentInfo) {
        this.patientName = consultContentInfo.getPatientId().getPatientName();
        this.consultDate = consultContentInfo.getConsultDate();
        this.consultContent = consultContentInfo.getConsultContent();
        this.takingDrug = consultContentInfo.getTakingDrug();
    }
}
