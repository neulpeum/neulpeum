package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.DrugRepository;
import com.medicare.neulpeum.Repository.StoredDrugInfoRepository;
import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.domain.entity.StoredDrugInfo;
import com.medicare.neulpeum.dto.DrugRequestDto;
import com.medicare.neulpeum.dto.DrugResponseDto;
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
public class DrugServiceImpl implements DrugService{

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    StoredDrugInfoRepository storedDrugInfoRepository;

    @Override
    public void save(DrugRequestDto diReq) {
        try {
            DrugInfo drugInfo = diReq.toEntity(diReq.getDrugName());
            DrugInfo savedDrugInfo = drugRepository.save(drugInfo);
        } catch (Exception e) {
            log.error("DrugInfo 저장 중 오류 발생: {}", e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugResponseDto> findAll() {
        List<DrugInfo> drugInfos = drugRepository.findAll();

        List<DrugResponseDto> DrugInfoResponseDtoList =
                drugInfos.stream().map(drugInfo -> {
                            StoredDrugInfo storedDrugInfo = storedDrugInfoRepository.findByDrugInfo(drugInfo)
                                    .orElseThrow(() -> new RuntimeException("StoredDrugInfo를 찾을 수 없습니다."));

                            return new DrugResponseDto(drugInfo, storedDrugInfo);
                        })
                        .collect(Collectors.toList());

        return DrugInfoResponseDtoList;
    }

//    @Override
//    public DrugResponseDto update(long id, DrugRequestDto drugInfoRequestDto) {
//        try {
//            Optional<DrugInfo> drugInfo = drugRepository.findById(id);
//            if (drugInfo.isPresent()) {
//                DrugInfo _druginfo = drugInfo.get();
//                _druginfo.setDrugName(drugInfoRequestDto.getDrugNameKor());
//                return new DrugResponseDto(_druginfo);
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    @Override
    public void delete(long id) {
        try {
            drugRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
