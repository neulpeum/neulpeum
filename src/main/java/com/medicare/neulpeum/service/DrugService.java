package com.medicare.neulpeum.service;

import com.medicare.neulpeum.dto.DrugNameAndAmountResponseDto;
import com.medicare.neulpeum.dto.DrugRequestDto;
import com.medicare.neulpeum.dto.DrugResponseDto;
import com.medicare.neulpeum.dto.DrugUpdateRequestDto;

import java.util.List;

public interface DrugService {
    void save(DrugRequestDto drugRequestDto);
    List<DrugResponseDto> findAll();
    List<DrugResponseDto> findByDrugName(String drugName);
    boolean existsByDrugId(Long id);
    void update(DrugRequestDto drugRequestDto);
    List<DrugNameAndAmountResponseDto> getDistinctDrugNameAndTotalUsableAmount();
    void updateUsedDrug(DrugUpdateRequestDto drugUpdateRequestDto);

}
