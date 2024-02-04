package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.PatientRepository;
import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.DrugResponseDto;
import com.medicare.neulpeum.dto.PatientRequestDto;
import com.medicare.neulpeum.dto.PatientResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponseDto> findAll() {
        List<PatientInfo> patientInfos = patientRepository.findAll();

        List<PatientResponseDto> patientResponseDtoList =
                patientInfos.stream().map(patientInfo -> new PatientResponseDto(patientInfo)).collect(Collectors.toList());

        return patientResponseDtoList;
    }

    @Override
    public List<PatientResponseDto> findAllByPatientName(String patientName) {
        List<PatientInfo> findPatient = patientRepository.findAllByPatientName(patientName);

        List<PatientResponseDto> patientResponseDtoList = findPatient.stream()
                .map(patientInfo -> new PatientResponseDto(patientInfo))
                .collect(Collectors.toList());

        return patientResponseDtoList;
    }
}
