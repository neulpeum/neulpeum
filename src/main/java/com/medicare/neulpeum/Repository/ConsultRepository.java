package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultRepository extends JpaRepository<ConsultContentInfo, Long> {
    List<ConsultContentInfo> findAllByPatientId(PatientInfo patientId);

    Optional<ConsultContentInfo> findByConsultId(Long consultId);
}
