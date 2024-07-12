package com.medicare.neulpeum.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConsultRequestDto {
    private Long patientId;
    private String providerName;
    private String takingDrug;
    private String consultContent;
    private String consultedAt;
}
