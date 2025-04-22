package com.cdp.service;

import com.cdp.domain.model.SalesInvoicePosting;
import com.cdp.domain.repository.SalesInvoicePostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesInvoiceApi {

    @Autowired
    private SalesInvoicePostingRepository salesInvoicePostingRepository;

    public List<SalesInvoicePosting> create(List<SalesInvoicePosting> salesInvoicePosting) {

        try{
            return salesInvoicePostingRepository.saveAll(salesInvoicePosting);
        }catch (Exception e){
            return null;
        }
    }

    public SalesInvoicePosting getBySalesOrderId(String salesOrderId) {
        return salesInvoicePostingRepository.findAllBySalesOrderId(salesOrderId).get(0);
    }
}
