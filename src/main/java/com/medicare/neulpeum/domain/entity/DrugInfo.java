package com.medicare.neulpeum.domain.entity;

import com.medicare.neulpeum.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "drugInfo")
public class DrugInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "drugId")
    private Long id;

    @Column(nullable = false)
    private String drugName;

    @Column(nullable = false)
    private LocalDate expireDate;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime stockDate; // 입고 날짜

    @Column(nullable = false)
    private Integer usableAmount;
}
