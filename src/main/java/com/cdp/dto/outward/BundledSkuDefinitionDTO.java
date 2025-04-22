package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BundledSkuDefinitionDTO {
    private String bundledParentBarcode;
    private String bundledParentChannelSkuId;
    private String bundledParentClientSkuId;
    private List<ChildSkuDTO> childSkus = new ArrayList<>();

    // Getters with null checks
    public List<ChildSkuDTO> getChildSkus() {
        return childSkus != null ? childSkus : new ArrayList<>();
    }
}