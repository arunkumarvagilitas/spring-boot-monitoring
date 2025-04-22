package com.cdp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesInvoicePosting {
    @Id
    private String id;

    private String customer;
    // Reference IDs
    private String salesOrderId;
    private String returnOrderId;
    private String deliveryNoteId;
    private String salesInvoiceId;
    private String returnSalesInvoiceId;

    // Product information
    private String skuCode;
    private Integer qty;

    // Tax components
    private Double cgstAmount;
    private Double sgstAmount;
    private Double igstAmount;
    private Double taxableAmount;

    // Timestamp
    private String timestamp;

}