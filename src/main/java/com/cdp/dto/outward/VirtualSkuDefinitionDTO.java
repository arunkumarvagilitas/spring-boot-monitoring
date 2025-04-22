package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualSkuDefinitionDTO {
    private String virtualParentChannelSkuId;
    private String virtualParentBarcode;
    private List<ChildSkuDTO> childSkus = new ArrayList<>();

    public List<ChildSkuDTO> getChildSkus() {
        return childSkus != null ? childSkus : new ArrayList<>();
    }
}