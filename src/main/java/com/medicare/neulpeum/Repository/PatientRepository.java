package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientInfo, Long> {
    @Override
    List<PatientInfo> findAll();
}
