package com.cdp.dto.outward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Long orderId;
    private String partnerCode;
    private String partnerLocationCode;
    private String locationCode;
    private Long messageId;
    private String orderCode;
    private String parentOrderCode;
    private String orderTime;
    private String orderType;
    private String paymentMethod;
    private Boolean isPriority = false;

    @NotBlank(message = "channelName is required")
    @NotNull(message = "channelName is can not be null")
    private String channelName;
    private String channelType;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
    private String eventType;
    private String eventTime;
    private List<ShipmentDTO> shipments = new ArrayList<>();

    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private List<VirtualSkuDefinitionDTO> virtualSkuDefinitions = new ArrayList<>();
    private Object isSplitRequired; // Boolean or String
    private Object shippingCharges;// Double or null

    @NotNull(message = "omsOrderId is required")
    @Positive(message = "omsOrderId must be a positive number")
    private Long omsOrderId;
    private String turnAroundTime;
    private Map<String, Object> orderCustomAttributes = new HashMap<>();
    private List<BundledSkuDefinitionDTO> bundledSkuDefinitions = new ArrayList<>();
    private ShipmentDetailsDTO shipmentDetails;

    // Getters and setters
    public List<OrderItemDTO> getOrderItems() {
        return orderItems != null ? orderItems : new ArrayList<>();
    }

    public List<ShipmentDTO> getShipments() {
        return shipments != null ? shipments : new ArrayList<>();
    }

    public Map<String, Object> getOrderCustomAttributes() {
        return orderCustomAttributes != null ? orderCustomAttributes : new HashMap<>();
    }
}