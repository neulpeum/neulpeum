package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.ConsultRepository;
import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.ConsultRequestDto;
import com.medicare.neulpeum.dto.ConsultResponseDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ConsultServiceImpl implements ConsultService{
    
    @Autowired
    ConsultRepository consultRepository;

    @Override
    public void save(ConsultRequestDto consultReq) {
        try {
            ConsultContentInfo consultContentInfo = consultReq.toEntity();
            consultRepository.save(consultContentInfo);
        } catch (Exception e) {
            log.error("ConsultContentInfo 저장 중 오류 발생: {}", e.getMessage());
        }
    }

    @Override
    public List<ConsultResponseDto> findAllByPatientId(PatientInfo patientId) {
        List<ConsultContentInfo> getConsult = consultRepository.findAllByPatientId(patientId);

        List<ConsultResponseDto> consultResponseDtoList = getConsult.stream()
                .map(consultContentInfo -> new ConsultResponseDto(consultContentInfo))
                .collect(Collectors.toList());
        return consultResponseDtoList;
    }


}
