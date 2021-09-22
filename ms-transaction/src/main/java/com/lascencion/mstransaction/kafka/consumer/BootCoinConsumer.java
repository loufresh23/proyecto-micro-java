package com.lascencion.mstransaction.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.mstransaction.domain.dto.PayDto;
import com.lascencion.mstransaction.domain.dto.TransactionDto;
import com.lascencion.mstransaction.domain.entity.Transaction;
import com.lascencion.mstransaction.service.inv.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BootCoinConsumer {

    @Autowired
    private TransactionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${custom.kafka.topic-name-pay}",
            groupId = "${custom.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumer(PayDto payDto) {

        TransactionDto transactionDto = new TransactionDto();
        this.getTransaction(transactionDto)
                .flatMap(a -> {
                    a.setInvoiceId(payDto.getIdInvoice());
                    a.setAmount(payDto.getAmount());
                    a.setCreateAt(payDto.getCreateAt());
                    log.info("********" + a.toString());
                    return service.create(a);
                }).subscribe();

        log.info("Mensaje consumido [{}]", payDto);
    }

    public Mono<Transaction> getTransaction(TransactionDto transactionDto) {
        return Mono.just(objectMapper.convertValue(transactionDto, Transaction.class));
    }

}
