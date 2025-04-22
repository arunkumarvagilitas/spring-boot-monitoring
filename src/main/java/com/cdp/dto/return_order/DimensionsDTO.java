package com.cdp.dto.return_order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DimensionsDTO {
    private Double length;
    private Double breadth;
    private Double height;
    private Double weight;
}
