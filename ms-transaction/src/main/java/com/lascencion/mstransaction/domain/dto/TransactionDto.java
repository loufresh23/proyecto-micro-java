package com.lascencion.mstransaction.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String id;

    private Long invoiceId;

    private Double amount;

    private LocalDateTime createAt;

}
