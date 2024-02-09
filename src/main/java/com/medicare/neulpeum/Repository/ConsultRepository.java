package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.controller.ConsultController;
import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import com.medicare.neulpeum.domain.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultRepository extends JpaRepository<ConsultContentInfo, Long> {
    List<ConsultContentInfo> findAllByPatientId(PatientInfo patientId);
}
