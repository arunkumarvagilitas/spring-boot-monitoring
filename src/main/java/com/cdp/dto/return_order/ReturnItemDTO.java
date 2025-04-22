package com.cdp.dto.return_order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnItemDTO {
    private String itemCode;
    private String returnReason;
    private String channelSkuCode;
    private String returnOrderItemStatus;
    private Double channelDiscount;
    private Double sellerDiscount;
    private Double giftChangePerUnit;
    private String qcStatus;
    private String qcReason;
    private List<String> imageUrls;
    private Double sellingPricePerUnit;
    private Boolean isExcess;
    private Map<String, String> returnOrderItemAttributes;
    private List<VirtualSkuChildItemDTO> virtualSkuChildItemReturnPostings;
    private List<BundledSkuChildItemDTO> bundledSkuChildItemReturnPostings;
    private List<ReturnItemDTO> returnItemDTOS;

    // Getters and setters
}