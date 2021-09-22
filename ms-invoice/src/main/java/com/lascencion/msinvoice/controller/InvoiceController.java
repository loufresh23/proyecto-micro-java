package com.lascencion.msinvoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.msinvoice.domain.dto.InvoiceDto;
import com.lascencion.msinvoice.domain.entity.Invoice;
import com.lascencion.msinvoice.kafka.producer.Producer;
import com.lascencion.msinvoice.service.inv.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Autowired
    private Producer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody Invoice invoice) {
        invoice.setStatus("CREATED");
        InvoiceDto oInvoiceDto = service.save(invoice);
        producer.producer(oInvoiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(oInvoiceDto);
    }

}
