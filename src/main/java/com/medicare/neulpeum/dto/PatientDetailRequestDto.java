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
public class PatientDetailRequestDto {
    private Long patientId;
    private String patientName;
    private String birthDate;
    private String phoneNum;
    private String address;
    private String disease;
    private String specialReport;

    public PatientInfo toEntity(PatientDetailRequestDto pdReq) {
        return PatientInfo.builder()
                .id(pdReq.getPatientId())
                .patientName(pdReq.getPatientName())
                .birthDate(pdReq.getBirthDate())
                .phoneNum(pdReq.getPhoneNum())
                .address(pdReq.getAddress())
                .disease(pdReq.getDisease())
                .specialReport(pdReq.getSpecialReport())
                .build();
    }
}
