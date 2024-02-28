package com.medicare.neulpeum.service;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import com.medicare.neulpeum.dto.*;

import java.util.List;

public interface ConsultService {
    void save(ConsultRequestDto consultRequestDto);

    List<ConsultResponseDto> findAllByPatientId(PatientInfo patientId);

    ConsultDetailResponseDto findByConsultId(Long consultId);

    void update(ConsultUpdateRequestDto consultUpdateRequestDto);
}
