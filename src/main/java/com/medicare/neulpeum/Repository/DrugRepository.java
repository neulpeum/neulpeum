package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.DrugInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<DrugInfo, Long> {
    @Override
    List<DrugInfo> findAll();
    List<DrugInfo> findByDrugName(String drugName);
    boolean existsById(Long id);
}
