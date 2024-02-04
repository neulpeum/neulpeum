package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "usedDrugInfo")
public class UsedDrugInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "usedDrugId")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "drugId", nullable = false)
    private DrugInfo drugId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private PatientInfo patientId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "consultId", nullable = false)
    private ConsultContentInfo consultId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "expireDate", nullable = false)
    private DrugInfo expireDate;

    @Column(nullable = false)
    private Date usedDate;

    @Column(nullable = false)
    private Integer usedAmount;

    @Column(nullable = false)
    private String providerName;
}
