package com.cdp.controller;

import com.cdp.domain.model.ApiEvent;
import com.cdp.domain.model.ApiEventStatus;
import com.cdp.domain.model.SalesInvoicePosting;
import com.cdp.domain.repository.ApiEventRepository;
import com.cdp.service.SalesInvoiceApi;
import com.cdp.utils.Constant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/invoice")
@RestController
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceApi salesInvoiceApi;

    @Autowired
    private ApiEventRepository apiEventRepository;

    @PostMapping()
    public List<SalesInvoicePosting> create(@RequestBody String salesInvoicePostingsJson){

        ApiEvent apiEventLog = createApiEventLog(salesInvoicePostingsJson);
        try{
            List<SalesInvoicePosting> salesInvoicePostings = new ObjectMapper().readValue(salesInvoicePostingsJson, new TypeReference<List<SalesInvoicePosting>>() {});
            return salesInvoiceApi.create(salesInvoicePostings);
        }catch (Exception e){
            apiEventLog.setStatus(ApiEventStatus.DB_FAILED);
            apiEventLog.setErrorMessage(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{salesOrderId}")
    public SalesInvoicePosting get(@PathVariable String salesOrderId){
        return salesInvoiceApi.getBySalesOrderId(salesOrderId);
    }

    private ApiEvent createApiEventLog(String salesInvoicePostings) {
        ApiEvent apiEvent = new ApiEvent();
        apiEvent.setPayload(salesInvoicePostings);
        apiEvent.setRequestUrl(Constant.INVOICE_POSTING);
        apiEvent.setStatus(ApiEventStatus.DB_SUCCESS);
        return apiEventRepository.save(apiEvent);
    }

}
