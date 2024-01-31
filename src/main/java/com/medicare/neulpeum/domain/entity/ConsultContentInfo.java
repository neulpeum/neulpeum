package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "consultContentInfo")
public class ConsultContentInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "consultId")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private PatientInfo patientInfo;

    @Column(nullable = false)
    private Date consultDate;

    @Column(nullable = false)
    private String providerName;

    @Column(nullable = false)
    private String consultContent;

    @Column(nullable = false)
    private Date consultModifyDate;
}
