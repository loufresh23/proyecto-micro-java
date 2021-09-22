package com.lascencion.mspay.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.mspay.domain.dto.InvoiceDto;
import com.lascencion.mspay.domain.entity.Invoice;
import com.lascencion.mspay.domain.entity.Pay;
import com.lascencion.mspay.kafka.producer.Producer;
import com.lascencion.mspay.service.inv.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class PayController {

    @Autowired
    private PayService service;

    @Autowired
    private Producer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pay pay) throws JsonProcessingException {

        InvoiceDto    invoiceDto       = service.findByIdInvoice(pay.getIdInvoice());
        ErrorResponse errorResponse = new ErrorResponse();

        if (pay.getIdInvoice() == null || invoiceDto == null) {
            errorResponse.setMensaje("No se encontró invoice");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        if (invoiceDto.getAmount().equals(pay.getAmount()) && invoiceDto.getStatus().equals("CREATED")) {

            pay.setCreateAt(LocalDateTime.now());
            pay.setStatus("OK");
            service.create(pay);

            invoiceDto.setStatus("PAYED");
            Invoice invoice = objectMapper.convertValue(invoiceDto, Invoice.class);
            service.createInvoice(invoice);

            this.producer.producer(service.getPayDto(pay));
            return ResponseEntity.status(HttpStatus.CREATED).body(pay);
        }

        errorResponse.setMensaje("El monto es incorrecto o la factura ya se encuentra pagada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

}
