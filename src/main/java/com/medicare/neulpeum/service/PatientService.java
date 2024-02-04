package com.medicare.neulpeum.service;

import com.medicare.neulpeum.dto.PatientRequestDto;
import com.medicare.neulpeum.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {
    void save(PatientRequestDto patientRequestDto);

    List<PatientResponseDto> findAll();
}
