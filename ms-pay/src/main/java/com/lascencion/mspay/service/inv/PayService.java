package com.lascencion.mspay.service.inv;

import com.lascencion.mspay.domain.dto.InvoiceDto;
import com.lascencion.mspay.domain.dto.PayDto;
import com.lascencion.mspay.domain.entity.Invoice;
import com.lascencion.mspay.domain.entity.Pay;

import java.util.List;

public interface PayService {

    List<PayDto> findAll();

    PayDto create(Pay pay);

    PayDto getPayDto(Pay pay);

    // Invoice
    InvoiceDto createInvoice(Invoice invoice);

    InvoiceDto findByIdInvoice(Long id);

    InvoiceDto getInvoiceDto(Invoice invoice);
}
