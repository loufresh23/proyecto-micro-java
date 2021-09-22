package com.lascencion.mstransaction.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String id;

    private Long invoiceId;

    private Double amount;

    private LocalDateTime createAt;

}
