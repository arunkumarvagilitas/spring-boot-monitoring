package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkuQuantityDataDTO {
    private Long globalSkuId;
    private String clientSkuId;
    private Integer count = 0;

    // Getters with null checks
    public Integer getCount() {
        return count != null ? count : 0;
    }
}