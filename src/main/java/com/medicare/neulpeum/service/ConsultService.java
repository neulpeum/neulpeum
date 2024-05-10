package com.medicare.neulpeum.service;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.domain.entity.ProvidedDrugInfo;
import com.medicare.neulpeum.dto.ConsultDetailResponseDto;
import com.medicare.neulpeum.dto.ConsultRequestDto;
import com.medicare.neulpeum.dto.ConsultResponseDto;
import com.medicare.neulpeum.dto.ConsultUpdateRequestDto;

import java.util.List;

public interface ConsultService {
    Long save(ConsultRequestDto consultRequestDto);

    List<ConsultResponseDto> findAllByPatientId(PatientInfo patientId);

    ConsultDetailResponseDto findByConsultId(Long consultId);

    void update(ConsultUpdateRequestDto consultUpdateRequestDto);

    void delete(Long consultId);

}
