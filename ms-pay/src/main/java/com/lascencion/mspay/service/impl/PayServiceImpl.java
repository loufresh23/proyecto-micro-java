package com.lascencion.mspay.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lascencion.mspay.domain.dto.InvoiceDto;
import com.lascencion.mspay.domain.dto.PayDto;
import com.lascencion.mspay.domain.entity.Invoice;
import com.lascencion.mspay.domain.entity.Pay;
import com.lascencion.mspay.domain.repository.InvoiceRepository;
import com.lascencion.mspay.domain.repository.PayRepository;
import com.lascencion.mspay.service.inv.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<PayDto> findAll() {
        List<Pay> payList = payRepository.findAll();
        return this.getListPayDTO(payList);
    }

    @Override
    public PayDto create(Pay pay) {
        return this.getPayDto(payRepository.save(pay));
    }

    @Override
    public InvoiceDto createInvoice(Invoice invoice) {
        return this.getInvoiceDto(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceDto findByIdInvoice(Long id) {
        return this.getInvoiceDto(invoiceRepository.findById(id).orElse(null));
    }

    @Override
    public InvoiceDto getInvoiceDto(Invoice invoice) {
        return objectMapper.convertValue(invoice, InvoiceDto.class);
    }

    @Override
    public PayDto getPayDto(Pay pay) {
        return objectMapper.convertValue(pay, PayDto.class);
    }

    private List<PayDto> getListPayDTO(List<Pay> lstPay) {
        List<PayDto> lstPayDTO = new ArrayList<PayDto>();
        lstPay.forEach(pay -> {
            PayDto payDto = new PayDto();
            BeanUtils.copyProperties(pay, payDto);
            lstPayDTO.add(payDto);
        });
        return lstPayDTO;
    }

}
