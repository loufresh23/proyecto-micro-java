package com.lascencion.mspay.domain.repository;

import com.lascencion.mspay.domain.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
