package com.cdp.dto.return_order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BundledSkuChildItemDTO {
    private String barcode;
    private String clientSkuCode;
    private String channelSkuCode;
    private Long omsReturnItemId;
    private String qcReason;
    private String qcStatus;
    private String returnOrderItemStatus;
}