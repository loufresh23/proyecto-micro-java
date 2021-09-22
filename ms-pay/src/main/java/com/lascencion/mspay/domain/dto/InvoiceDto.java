package com.lascencion.mspay.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    private Long id;

    private String description;

    private Double amount;

    private String status;

}
