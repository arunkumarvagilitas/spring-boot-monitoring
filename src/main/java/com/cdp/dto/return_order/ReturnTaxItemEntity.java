package com.cdp.dto.return_order;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnTaxItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long returnOrderId;
    private Long taxItemId;
    private String taxType;
    private Double taxRate;
    private Double taxAmountPerUnit;
    private Double taxAmountTotal;
    private Long returnOrderItemId;
    private String itemCode;
    private Long shipmentItemId;

}

