package com.cdp.domain.repository;

import com.cdp.domain.model.SalesInvoicePosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesInvoicePostingRepository extends JpaRepository<SalesInvoicePosting, String> {
    List<SalesInvoicePosting> findAllBySalesOrderId(String salesOrderId);
}
