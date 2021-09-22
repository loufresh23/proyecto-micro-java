package com.lascencion.mspay.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.mspay.domain.dto.InvoiceDto;
import com.lascencion.mspay.domain.entity.Invoice;
import com.lascencion.mspay.service.inv.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PayService service;

    @KafkaListener(
            topics = "${custom.kafka.topic-name-invoice}",
            groupId = "${custom.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumer(InvoiceDto mensaje) {
        log.info("Mensaje consumido [{}]", mensaje);
        Invoice invoice = this.getInvoice(mensaje);
        service.createInvoice(invoice);
    }

    public Invoice getInvoice(InvoiceDto invoiceDto){
        return objectMapper.convertValue(invoiceDto, Invoice.class);
    }

}
