package com.lascencion.mstransaction.domain.repository;

import com.lascencion.mstransaction.domain.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
}
