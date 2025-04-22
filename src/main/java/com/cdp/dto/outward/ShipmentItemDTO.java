package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipmentItemDTO {
    private String channelSkuCode;
    private String orderItemCode;
    private Double channelDiscount = 0.0;
    private Double netTaxAmountPerUnit = 0.0;
    private Double netTaxAmountTotal = 0.0;
    private Double baseSellingPricePerUnit = 0.0;
    private Double baseSellingPriceTotal = 0.0;
    private Double sellingPricePerUnit = 0.0;
    private Double sellingPriceTotal = 0.0;
    private Integer quantity = 0;
    private Double shippingChargePerUnit = 0.0;
    private List<TaxItemDTO> taxItems = new ArrayList<>();
    private String barcode;
    private String clientSkuCode;

    public List<TaxItemDTO> getTaxItems() {
        return taxItems != null ? taxItems : new ArrayList<>();
    }
}