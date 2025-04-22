package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemDTO {
    private String orderItemCode;
    private String channelSkuCode;
    private Integer orderedQuantity = 0;
    private Integer cancelledQuantity = 0;
    private Integer customerCancelledQty = 0;
    private Integer sellerCancelledQty = 0;
    private Integer sellerRejectQty = 0;
    private Double channelDiscount = 0.0;
    private Double sellerDiscount = 0.0;
    private Double sellingPricePerUnit = 0.0;
    private Double giftChargePerUnit = 0.0;
    private Double shippingCharge = 0.0;
    private Long omsItemId;
    private String barcode;
    private String clientSkuCode;
    private Map<String, Object> orderItemCustomAttributes = new HashMap<>();

    // Getters with null checks
    public Map<String, Object> getOrderItemCustomAttributes() {
        return orderItemCustomAttributes != null ? orderItemCustomAttributes : new HashMap<>();
    }
}
