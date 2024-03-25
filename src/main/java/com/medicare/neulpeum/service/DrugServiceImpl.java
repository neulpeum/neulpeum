package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.DrugRepository;
import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.dto.DrugNameAndAmountResponseDto;
import com.medicare.neulpeum.dto.DrugRequestDto;
import com.medicare.neulpeum.dto.DrugResponseDto;
import com.medicare.neulpeum.dto.DrugUpdateRequestDto;
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

    @Override
    public void save(DrugRequestDto drugReq) {
        try {
            drugRepository.save(drugReq.toEntity(drugReq));
        } catch (Exception e) {
            log.error("DrugInfo 저장 중 오류 발생: {}", e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugResponseDto> findAll() {
        List<DrugInfo> drugInfos = drugRepository.findAll();

        List<DrugResponseDto> drugResponseDtoList =
                drugInfos.stream().map(
                        drugInfo -> new DrugResponseDto(drugInfo)).collect(Collectors.toList()
                );

        return drugResponseDtoList;
    }

    @Override
    public List<DrugResponseDto> findByDrugName(String drugName) {
        List<DrugInfo> findDrug = drugRepository.findByDrugName(drugName);

        List<DrugResponseDto> drugResponseDtoList = findDrug.stream()
                .map(drugInfo -> new DrugResponseDto(drugInfo))
                .collect(Collectors.toList());

        return drugResponseDtoList;
    }

    @Override
    public boolean existsByDrugId(Long id) {
        return drugRepository.existsById(id);
    }

    @Override
    public void update(DrugRequestDto drugRequestDto) {
        Long drugId = drugRequestDto.getDrugId();
        Optional<DrugInfo> optionalDrug = drugRepository.findById(drugId);
        if (optionalDrug.isPresent()) {
            DrugInfo existingDrug = optionalDrug.get();
            existingDrug.setDrugName(drugRequestDto.getDrugName());
            existingDrug.setExpireDate(drugRequestDto.getExpireDate());
            existingDrug.setUsableAmount(drugRequestDto.getUsableAmount());
            drugRepository.save(existingDrug);
        } else {
            // 약물을 찾지 못한 경우에도 저장하도록 수정
            drugRepository.save(drugRequestDto.toEntity(drugRequestDto));
        }
    }

    @Override
    public List<DrugNameAndAmountResponseDto> getDistinctDrugNameAndTotalUsableAmount() {
        List<Object[]> result = drugRepository.findDistinctDrugNameAndTotalDrugAmount();

        return result.stream()
                .map(row -> new DrugNameAndAmountResponseDto((String) row[0], (Long) row[1]))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUsedDrug(DrugUpdateRequestDto drugUpdateRequestDto) {
        try {
            //약 이름으로 유통기한이 가장 짧은 것을 DB에서 조회
            List<DrugInfo> drugs = drugRepository.findByDrugNameOrderByExpireDateAsc(drugUpdateRequestDto.getDrugName());

            if (drugs.isEmpty()) {
                throw new IllegalArgumentException(drugUpdateRequestDto.getDrugName() + " 해당 약이 존재하지 않습니다.");
            }

            //유통기한이 가장 적게 남은 약을 가져옴
            DrugInfo upToDateDrug = drugs.get(0);
            int usedAmount = drugUpdateRequestDto.getUsedAmount();
            int usableAmount = upToDateDrug.getUsableAmount();

            //사용 가능 개수보다 사용 개수가 더 많을 경우 (나중에 그 다음으로 빠른 유통기한 재고에서 빠져나갈 수 있도록 수정)
            if (usableAmount < usedAmount) {
                throw new IllegalArgumentException("약 재고가 부족합니다. 사용 가능 개수: " + upToDateDrug.getUsableAmount());
            }

            //사용한 약 개수만큼 사용 가능 개수에서 빼서 업데이트
            upToDateDrug.setUsableAmount(usableAmount - usedAmount);
            drugRepository.save(upToDateDrug);
        } catch (Exception e) {
            log.error("약 재고 업데이트 중 오류 발생: {}" + e.getMessage());
            throw new RuntimeException("약 재고 업데이트 중 오류 발생: " + e.getMessage());
        }

    }


}
