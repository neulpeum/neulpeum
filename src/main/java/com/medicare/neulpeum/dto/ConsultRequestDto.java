package com.medicare.neulpeum.dto;

import com.medicare.neulpeum.Repository.PatientRepository;
import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ConsultRequestDto {

    private String patientName;
    private String providerName;
    private String takingDrug;
    private String consultContent;
    private PatientRepository patientInfoRepository;


    public ConsultContentInfo toEntity(String providerName, String takingDrug, String consultContent) {
        //환자 정보 조회(이름)
        PatientInfo patientInfo = patientInfoRepository.findByPatientName(patientName);

        return ConsultContentInfo.builder()
                .patientName(patientInfo)
                .providerName(providerName)
                .takingDrug(takingDrug)
                .consultContent(consultContent)
                .build();


    }
}
