package com.lascencion.msinvoice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.msinvoice.domain.dto.InvoiceDto;
import com.lascencion.msinvoice.domain.dto.PayDto;
import com.lascencion.msinvoice.domain.entity.Invoice;
import com.lascencion.msinvoice.service.inv.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @Autowired
    private InvoiceService service;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${custom.kafka.topic-name-pay}",
            groupId = "${custom.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumer(PayDto payDto) {

        InvoiceDto invoice = service.findById(payDto.getIdInvoice());
        invoice.setStatus("PAYED");
        service.save(this.getInvoice(invoice));
        log.info("Mensaje consumido [{}]", payDto);
    }

    public Invoice getInvoice(InvoiceDto invoiceDto) {
        return objectMapper.convertValue(invoiceDto, Invoice.class);
    }

}
