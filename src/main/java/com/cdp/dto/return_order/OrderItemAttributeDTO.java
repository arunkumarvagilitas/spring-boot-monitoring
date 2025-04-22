package com.cdp.dto.return_order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemAttributeDTO {
    private String barcode;
    private String clientSkuCode;
    private String channelSkuCode;
    private String orderItemCode;
    private Map<String, String> orderItemCustomAttributes;
}