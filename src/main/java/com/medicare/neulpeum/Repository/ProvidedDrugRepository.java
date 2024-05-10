package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.ProvidedDrugInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvidedDrugRepository extends JpaRepository<ProvidedDrugInfo, Long> {
    List<ProvidedDrugInfo> findAllByConsultId(ConsultContentInfo consultContentInfo);

    @Transactional
    void deleteByConsultId(ConsultContentInfo consultContentInfo); // ProvidedDrugInfo 엔티티 삭제

}
