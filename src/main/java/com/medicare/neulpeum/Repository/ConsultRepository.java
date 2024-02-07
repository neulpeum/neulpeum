package com.medicare.neulpeum.Repository;

import com.medicare.neulpeum.controller.ConsultController;
import com.medicare.neulpeum.domain.entity.ConsultContentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository<ConsultContentInfo, Long> {
}
