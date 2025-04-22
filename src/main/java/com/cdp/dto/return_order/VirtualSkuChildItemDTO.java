package com.cdp.dto.return_order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualSkuChildItemDTO {
    private String omsReturnItemId;
    private String returnOrderItemStatus;
    private String channelSkuCode;
    private String qcStatus;
    private String qcReason;
    private String barcode;
    private String clientSkuCode;
}

