package com.medicare.neulpeum.service;

import com.medicare.neulpeum.dto.DrugNameResponseDto;
import com.medicare.neulpeum.dto.DrugRequestDto;
import com.medicare.neulpeum.dto.DrugResponseDto;

import java.util.List;

public interface DrugService {
    void save(DrugRequestDto drugRequestDto);
    List<DrugResponseDto> findAll();
    List<DrugResponseDto> findByDrugName(String drugName);
    boolean existsByDrugId(Long id);
    void update(DrugRequestDto drugRequestDto);

    List<DrugNameResponseDto> getDistinctDrugNames();
}
