package com.lascencion.msinvoice.domain.repository;

import com.lascencion.msinvoice.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
