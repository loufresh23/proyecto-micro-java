package com.lascencion.mstransaction.service.inv;

import com.lascencion.mstransaction.domain.dto.TransactionDto;
import com.lascencion.mstransaction.domain.entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Flux<TransactionDto> findAll();

    Mono<TransactionDto> create(Transaction transaction);
}
