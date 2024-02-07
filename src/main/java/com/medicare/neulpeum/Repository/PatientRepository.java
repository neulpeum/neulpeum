package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.domain.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientInfo, Long> {
    @Override
    List<PatientInfo> findAll();

    List<PatientInfo> findAllByPatientName(String patientName);

    List<PatientInfo> findByPatientId(Long id);

    PatientInfo findByPatientName(String patientName);//이름으로 검색해서 찾는게 상담추가에 필요해서 적긴 했는데 맞나?
}
