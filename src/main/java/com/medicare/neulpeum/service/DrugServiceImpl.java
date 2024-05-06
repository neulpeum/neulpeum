package com.medicare.neulpeum.service;

import com.medicare.neulpeum.Repository.ConsultRepository;
import com.medicare.neulpeum.Repository.DrugRepository;
import com.medicare.neulpeum.Repository.ProvidedDrugRepository;
import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.domain.entity.ProvidedDrugInfo;
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
    @Autowired
    ProvidedDrugRepository providedDrugRepository;
    @Autowired
    ConsultRepository consultRepository;

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
    public void updateUsedDrug(List<DrugUpdateRequestDto> drugUpdateRequestDtoList) {
        try {
            for (DrugUpdateRequestDto updateRequestDto : drugUpdateRequestDtoList) {
                String drugName = updateRequestDto.getDrugName();
                int usedAmount = updateRequestDto.getUsedAmount();
                ConsultContentInfo consultId = consultRepository.findByConsultId(updateRequestDto.getConsultId()).orElse(null);
                if (consultId == null) {
                    throw new IllegalArgumentException("상담내역을 찾을 수 없습니다. ID: " + updateRequestDto.getConsultId());
                }


                //약 이름으로 유통기한이 가장 짧은 것을 DB에서 조회
                List<DrugInfo> drugs = drugRepository.findByDrugNameOrderByExpireDateAsc(drugName);

                int remainingAmount = usedAmount;
                for (DrugInfo drug : drugs) {
                    int usableAmount = drug.getUsableAmount();
                    if (usableAmount >= remainingAmount) {
                        drug.setUsableAmount(usableAmount - remainingAmount);
                        drugRepository.save(drug);

                        //ProvidedDrugInfo 테이블에 저장

                        ProvidedDrugInfo providedDrugInfo = ProvidedDrugInfo.builder()
                                .drugId(drug)
                                .consultId(consultId)
                                .providedAmount(remainingAmount)
                                .build();
                        providedDrugRepository.save(providedDrugInfo);

                        break;
                    } else {
                        //사용 가능한 개수가 사용하려는 개수보다 적은 경우 약 재고를 0개로 변경 후
                        //다음으로 유통기한이 짧은 약 재고에서 마저 빠져나가야 하는 개수를 뺄 수 있도록 반복문 계속
                        remainingAmount -= usableAmount;
                        drug.setUsableAmount(0);
                        drugRepository.save(drug);

                        //ProvidedDrugInfo 테이블에 저장
                        ProvidedDrugInfo providedDrugInfo = ProvidedDrugInfo.builder()
                                .drugId(drug)
                                .consultId(consultId)
                                .providedAmount(usableAmount)
                                .build();
                        providedDrugRepository.save(providedDrugInfo);
                    }
                }
            }
        } catch (Exception e) {
            log.error("약 재고 업데이트 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("약 재고 업데이트 중 오류 발생: " + e.getMessage());
        }
    }
}
