package com.lascencion.msinvoice.service.inv;

import com.lascencion.msinvoice.domain.dto.InvoiceDto;
import com.lascencion.msinvoice.domain.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<InvoiceDto> findAll();

    InvoiceDto findById(Long id);

    InvoiceDto save(Invoice invoice);

}
