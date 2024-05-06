package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.ConsultRepository;
import com.medicare.neulpeum.Repository.PatientRepository;
import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.ConsultDetailResponseDto;
import com.medicare.neulpeum.dto.ConsultRequestDto;
import com.medicare.neulpeum.dto.ConsultResponseDto;
import com.medicare.neulpeum.dto.ConsultUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Slf4j
@Service
@Transactional
public class ConsultServiceImpl implements ConsultService{
    
    @Autowired
    ConsultRepository consultRepository;
    @Autowired
    PatientRepository patientRepository;



    @Override
    public Long save(ConsultRequestDto consultReq) {
        try {
            PatientInfo patientId = patientRepository.findById(consultReq.getPatientId()).orElse(null);
            if (patientId == null) {
                throw new IllegalArgumentException("환자를 찾을 수 없습니다. ID: " + consultReq.getPatientId());
            }

            ConsultContentInfo consultContentInfo = ConsultContentInfo.builder()
                    .patientId(patientId)
                    .providerName(consultReq.getProviderName())
                    .takingDrug(consultReq.getTakingDrug())
                    .consultContent(consultReq.getConsultContent())
                    .build();

            ConsultContentInfo savedConsult = consultRepository.save(consultContentInfo);
//            log.info("*********************" + savedConsult.getConsultId());
            return savedConsult.getConsultId();

        } catch (Exception e) {
            log.error("ConsultContentInfo 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("ConsultContentInfo 저장 중 오류 발생: " + e.getMessage());
        }

//        try {
//            PatientInfo patientId = patientRepository.findById(consultReq.getPatientId()).get();
//            ConsultContentInfo consultContentInfo = ConsultContentInfo.builder()
//                    .patientId(patientId)
//                    .providerName(consultReq.getProviderName())
//                    .takingDrug(consultReq.getTakingDrug())
//                    .consultContent(consultReq.getConsultContent())
//                    .build();
//            consultRepository.save(consultContentInfo);
//        } catch (Exception e) {
//            log.error("ConsultContentInfo 저장 중 오류 발생: {}", e.getMessage());
//        }
    }



    @Override
    public List<ConsultResponseDto> findAllByPatientId(PatientInfo patientId) {
        List<ConsultContentInfo> getConsult = consultRepository.findAllByPatientId(patientId);

        List<ConsultResponseDto> consultResponseDtoList = getConsult.stream()
                .map(consultContentInfo -> new ConsultResponseDto(consultContentInfo))
                .collect(Collectors.toList());
        return consultResponseDtoList;
    }

    @Override
    public ConsultDetailResponseDto findByConsultId(Long consultId) {
        Optional<ConsultContentInfo> consultContentInfoOptional = consultRepository.findById(consultId);
        return consultContentInfoOptional.map(ConsultDetailResponseDto::new).orElse(null);
    }

    @Override
    public void update(ConsultUpdateRequestDto consultUpdateRequestDto) {
        try {
            Optional<ConsultContentInfo> optionalConsultContentInfo = consultRepository.findByConsultId(consultUpdateRequestDto.getConsultId());
            if (optionalConsultContentInfo.isPresent()) {
                ConsultContentInfo consultContentInfo = optionalConsultContentInfo.get();
                //주어진 DTO에서 새로운 정보 추출하여 업데이트
                consultContentInfo.setProviderName(consultUpdateRequestDto.getProviderName());
                consultContentInfo.setTakingDrug(consultUpdateRequestDto.getTakingDrug());
                consultContentInfo.setConsultContent(consultUpdateRequestDto.getConsultContent());

                //업데이트 된 정보 저장
                consultRepository.save(consultContentInfo);
            } else {
                throw new IllegalArgumentException("상담 내역을 찾을 수 없습니다. ID: " + consultUpdateRequestDto.getConsultId());
            }
        } catch (Exception e) {
            log.error("환자 정보 수정 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("환자 정보 수정 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long consultId) {
        Optional<ConsultContentInfo> optionalConsultContentInfo = consultRepository.findByConsultId(consultId);
        if (optionalConsultContentInfo.isPresent()) {
            consultRepository.deleteByConsultId(consultId);
        }
    }
}
