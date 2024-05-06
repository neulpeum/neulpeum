package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.ProvidedDrugInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvidedDrugRepository extends JpaRepository<ProvidedDrugInfo, Long> {

}
