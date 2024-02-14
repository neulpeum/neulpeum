package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patientInfo")
public class PatientInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "patientId")
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column
    private String birthDate;

    @Column
    private String phoneNum;

    @Column(nullable = false)
    private String address;

    @Column
    private String specialReport;

    @Column
    private String takingDrug;

    @Column
    private String disease;

}

