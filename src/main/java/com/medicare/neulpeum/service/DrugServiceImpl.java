package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.DrugRepository;
import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.dto.DrugRequestDto;
import com.medicare.neulpeum.dto.DrugResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DrugServiceImpl implements DrugService{

    @Autowired
    DrugRepository drugRepository;

    @Override
    public void save(DrugRequestDto diReq) {
        try {
            DrugInfo drugInfo = diReq.toEntity(
                    diReq.getDrugName(),
                    diReq.getExpireDate(),
                    diReq.getStockAmount(),
                    diReq.getUsableAmount(),
                    diReq.getUsedAmount());
            DrugInfo savedDrugInfo = drugRepository.save(drugInfo);
        } catch (Exception e) {
            log.error("DrugInfo 저장 중 오류 발생: {}", e.getMessage());
        }
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<DrugResponseDto> findAll() {
//        List<DrugInfo> drugInfos = drugRepository.findAll();
//
//        List<DrugResponseDto> DrugInfoResponseDtoList =
//                drugInfos.stream().map(drugInfo -> {
//                            DrugInfo drugInfo1 = DrugRepository.()
//                                    .orElseThrow(() -> new RuntimeException("저장된 DrugInfo를 찾을 수 없습니다."));
//                            return new DrugResponseDto(drugInfo);
//                        })
//                        .collect(Collectors.toList());
//
//        return DrugInfoResponseDtoList;
//    }
//
//    @Override
//    public List<DrugResponseDto> findByDrugName(String drugName) {
//        List<DrugInfo> findDrug = drugRepository.findByDrugName(drugName);
//
//        List<DrugResponseDto> drugResponseDtoList = findDrug.stream()
//                .filter(drugInfo -> drugInfo.getDrugName().equals(drugName))
//                .map(drugInfo -> {
//                    StoredDrugInfo storedDrugInfo = storedDrugInfoRepository.findByDrugInfo(drugInfo)
//                            .orElseThrow(() -> new RuntimeException(drugName + "을 찾을 수 없습니다."));
//
//                    return new DrugResponseDto(drugInfo, storedDrugInfo);
//                })
//                .collect(Collectors.toList());
//
//        return drugResponseDtoList;
//    }
}
