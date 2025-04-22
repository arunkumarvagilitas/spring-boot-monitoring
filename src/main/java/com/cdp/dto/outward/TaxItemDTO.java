package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxItemDTO {
    private String type;
    private Double rate = 0.0;
    private Double taxPerUnit = 0.0;
    private Double taxTotal = 0.0;

    public Double getRate() {
        return rate != null ? rate : 0.0;
    }

    public Double getTaxPerUnit() {
        return taxPerUnit != null ? taxPerUnit : 0.0;
    }

    public Double getTaxTotal() {
        return taxTotal != null ? taxTotal : 0.0;
    }
}
