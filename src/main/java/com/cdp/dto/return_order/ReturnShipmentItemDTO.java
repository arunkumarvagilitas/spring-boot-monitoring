package com.cdp.dto.return_order;


import com.cdp.dto.outward.TaxItemDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnShipmentItemDTO {
    private String channelSkuCode;
    private String orderItemCode;
    private Integer quantity;
    private List<String> externalSerialCodes;
    private DimensionsDTO skuDimension;
    private Map<String, Object> orderItemCustomAttributes;
    private List<TaxItemDTO> taxItems;
    private Double baseSellingPricePerUnit;
    private Double baseSellingPriceTotal;
    private Double sellingPricePerUnit;
    private Double sellingPriceTotal;
}
