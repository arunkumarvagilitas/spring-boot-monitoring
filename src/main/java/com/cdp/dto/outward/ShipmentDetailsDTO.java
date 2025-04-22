package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentDetailsDTO {
    private Double length;
    private Double breadth;
    private Double height;
    private Double weight;
    private Long shipmentId;
    private Integer totalItemQuantity = 0;
    private List<PackboxDetailsDTO> packboxDetailsList = new ArrayList<>();

    public List<PackboxDetailsDTO> getPackboxDetailsList() {
        return packboxDetailsList != null ? packboxDetailsList : new ArrayList<>();
    }
}