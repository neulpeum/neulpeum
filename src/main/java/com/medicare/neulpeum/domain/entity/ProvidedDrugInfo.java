package com.medicare.neulpeum.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "providedDrugInfo")
public class ProvidedDrugInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "providedId")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "drugInfo", nullable = false)
    private DrugInfo drugId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "consultContentInfo", nullable = false)
    private ConsultContentInfo consultId;

    @Column(nullable = false)
    private int providedAmount;
}
