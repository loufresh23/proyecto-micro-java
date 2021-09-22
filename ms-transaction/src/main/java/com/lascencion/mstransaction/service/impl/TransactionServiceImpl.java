package com.lascencion.mstransaction.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.mstransaction.domain.dto.TransactionDto;
import com.lascencion.mstransaction.domain.entity.Transaction;
import com.lascencion.mstransaction.domain.repository.TransactionRepository;
import com.lascencion.mstransaction.service.inv.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Flux<TransactionDto> findAll() {
        return repository.findAll().flatMap(this::getTransactionDto);
    }

    @Override
    public Mono<TransactionDto> create(Transaction transaction) {
        return repository.save(transaction).flatMap(this::getTransactionDto);
    }

    public Mono<TransactionDto> getTransactionDto(Transaction transaction){
        return Mono.just(objectMapper.convertValue(transaction, TransactionDto.class));
    }

}
