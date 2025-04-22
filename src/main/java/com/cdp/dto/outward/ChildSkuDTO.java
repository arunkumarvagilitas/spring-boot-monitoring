package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChildSkuDTO {
    private String barcode;
    private String channelSkuCode;
    private String clientSkuCode;
    private Integer qty = 0;

    // Getters with null checks
    public Integer getQty() {
        return qty != null ? qty : 0;
    }
}