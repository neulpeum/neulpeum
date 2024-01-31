package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "patientInfo")
public class PatientInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "patientId")
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String address;

    private String specialReport;

    private String takingDrug;

    private String disease;

    @Column(nullable = false)
    private Date patientEnrollDate;

    @Column(nullable = false)
    private Date patientModifyDate;
}

