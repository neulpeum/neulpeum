package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;

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
    @JoinColumn(name = "patientId", nullable = false, referencedColumnName = "patientID")
    private PatientInfo patientId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "patientName", nullable = false, referencedColumnName = "patientName")
    private PatientInfo patientName;

    @CreatedDate //객체가 생성된 생성일지 자동으로 처리
    @Column(nullable = false)
    private LocalDate consultDate;

    @Column(nullable = false)
    private String providerName;

    @Column(nullable = false)
    private String consultContent;

    @LastModifiedDate //최종 수정 시간 자동으로 처리
    @Column(nullable = false)
    private LocalDate consultModifyDate;

    @Column
    private String takingDrug;

}
