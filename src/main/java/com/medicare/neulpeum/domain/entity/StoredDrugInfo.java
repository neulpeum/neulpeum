package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "storedDrugInfo")
public class StoredDrugInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "storedDrugId")
    private Long id;

    @Column(nullable = false)
    private LocalDate expireDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "drugId", nullable = false)
    private DrugInfo drugInfo;

    @Column(nullable = false)
    private Date stockDate; // 입고 날짜

    @Column(nullable = false)
    private Integer stockAmount;

    private Integer usedAmount;

    @Column(nullable = false)
    private Integer usableAmount;
}

