package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.PatientRepository;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
                    patientReq.getPatientName(),
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

    @Override
    public PatientDetailResponseDto findByPatientId(Long patientId) {
        Optional<PatientInfo> patientInfoOptional = patientRepository.findById(patientId);
        return patientInfoOptional.map(PatientDetailResponseDto::new).orElse(null);
    }

    @Override
    public void update(PatientDetailRequestDto patientDetailRequestDto) {
        try {
            Optional<PatientInfo> optionalPatientInfo = patientRepository.findById(patientDetailRequestDto.getPatientId());
            if (optionalPatientInfo.isPresent()) {
                PatientInfo patientInfo = optionalPatientInfo.get();
                // 주어진 DTO에서 새로운 정보 추출하여 업데이트
                patientInfo.setPatientName(patientDetailRequestDto.getPatientName());
                patientInfo.setBirthDate(patientDetailRequestDto.getBirthDate());
                patientInfo.setPhoneNum(patientDetailRequestDto.getPhoneNum());
                patientInfo.setAddress(patientDetailRequestDto.getAddress());
                patientInfo.setDisease(patientDetailRequestDto.getDisease());
                patientInfo.setTakingDrug(patientDetailRequestDto.getTakingDrug());
                patientInfo.setSpecialReport(patientDetailRequestDto.getSpecialReport());
                // 업데이트된 정보 저장
                patientRepository.save(patientInfo);
            } else {
                throw new IllegalArgumentException("환자를 찾을 수 없습니다. ID: " + patientDetailRequestDto.getPatientId());
            }
        } catch (Exception e) {
            log.error("환자 정보 수정 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("환자 정보 수정 중 오류 발생: " + e.getMessage());
        }
    }
}
