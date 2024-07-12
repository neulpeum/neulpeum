package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultContentInfo")
public class ConsultContentInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "consultId")
    private Long consultId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private PatientInfo patientId;

    @Column
    private String providerName;

    @Column
    private String consultContent;

    @Column
    private String takingDrug;

    @Column
    private String consultedAt;

    @OneToMany(mappedBy = "consultId", cascade = CascadeType.REMOVE)
    private List<ProvidedDrugInfo> providedDrugInfos = new ArrayList<>();

}
