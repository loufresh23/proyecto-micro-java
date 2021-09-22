package com.lascencion.mspay.domain.repository;

import com.lascencion.mspay.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
