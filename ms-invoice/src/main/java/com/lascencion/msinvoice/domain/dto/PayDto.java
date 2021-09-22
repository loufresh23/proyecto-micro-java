package com.lascencion.msinvoice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDto {

    private Long id;

    private Long idInvoice;

    private Double amount;

    private String status;

    private LocalDateTime createAt;

}