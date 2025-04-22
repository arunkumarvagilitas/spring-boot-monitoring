package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackboxDetailsDTO {
    private Double length;
    private Double breadth;
    private Double height;
    private Double weight;
    private Long boxId;
    private String awbNumber;
    private String packBoxRemarks;
    private Double volWeight;
    private String boxSkuId;
    private String boxCode;
    private List<SkuQuantityDataDTO> skuQuantityDataList = new ArrayList<>();

    // Getters with null checks
    public List<SkuQuantityDataDTO> getSkuQuantityDataList() {
        return skuQuantityDataList != null ? skuQuantityDataList : new ArrayList<>();
    }

    // Null-safe getters for numeric fields
    public Double getLength() {
        return length != null ? length : 0.0;
    }

    public Double getBreadth() {
        return breadth != null ? breadth : 0.0;
    }

    public Double getHeight() {
        return height != null ? height : 0.0;
    }

    public Double getWeight() {
        return weight != null ? weight : 0.0;
    }
}