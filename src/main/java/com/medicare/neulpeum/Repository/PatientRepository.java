package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientInfo, Long> {
    @Override
    List<PatientInfo> findAll();

    List<PatientInfo> findAllByPatientName(String patientName);

    Optional<PatientInfo> findById(Long patientId);


}
