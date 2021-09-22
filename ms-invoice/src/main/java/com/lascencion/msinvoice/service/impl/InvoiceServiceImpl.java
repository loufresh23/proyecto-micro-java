package com.lascencion.msinvoice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.msinvoice.domain.dto.InvoiceDto;
import com.lascencion.msinvoice.domain.entity.Invoice;
import com.lascencion.msinvoice.domain.repository.InvoiceRepository;
import com.lascencion.msinvoice.service.inv.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<InvoiceDto> findAll() {
        List<Invoice> lstInvoice = repository.findAll();
        return this.getListInvoiceDTO(lstInvoice);
    }

    @Override
    public InvoiceDto findById(Long id) {
        Invoice invoice = repository.findById(id).orElse(null);
        return this.getInvoiceDto(invoice);
    }

    @Override
    public InvoiceDto save(Invoice invoice) {
        return this.getInvoiceDto(repository.save(invoice));
    }

//    Tools
    public InvoiceDto getInvoiceDto(Invoice invoice) {
        return objectMapper.convertValue(invoice, InvoiceDto.class);
    }

    private List<InvoiceDto> getListInvoiceDTO(List<Invoice> lstInvoice) {
        List<InvoiceDto> lstInvoiceDTO = new ArrayList<InvoiceDto>();
        lstInvoice.forEach(invoice -> {
            InvoiceDto invoiceDto = new InvoiceDto();
            BeanUtils.copyProperties(invoice, invoiceDto);
            lstInvoiceDTO.add(invoiceDto);
        });
        return lstInvoiceDTO;
    }

}
