package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import com.medicare.neulpeum.domain.entity.StoredDrugInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoredDrugInfoRepository extends JpaRepository<StoredDrugInfo, Long> {
    Optional<StoredDrugInfo> findByDrugInfo(DrugInfo drugInfo);
}
