package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.PatientRepository;
import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.PatientRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public void save(PatientRequestDto patientReq) {
        try {
            PatientInfo patientInfo =  patientReq.toEntity(
                    patientReq.getName(),
                    patientReq.getAddress(),
                    patientReq.getDisease(),
                    patientReq.getTakingDrug(),
                    patientReq.getSpecialReport());
            PatientInfo savedPatientInfo = patientRepository.save(patientInfo);
        } catch (Exception e) {
            log.error("PatientInfo 저장 중 오류 발생: {}", e.getMessage());
        }
    }
}
