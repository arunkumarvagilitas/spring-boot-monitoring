package com.cdp.dto.return_order;

import com.cdp.dto.outward.AddressDTO;
import com.cdp.dto.outward.BundledSkuDefinitionDTO;
import com.cdp.dto.outward.VirtualSkuDefinitionDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnOrderDTO {
    private Long id;
    private String partnerCode;
    private String locationCode;
    private String partnerLocationCode;
    private String channelName;
    private String returnOrderCode;
    private String forwardOrderCode;
    private String forwardParentOrderCode;
    private String returnCreationTime;
    private String returnGateEntryCreationTime;
    private Long messageId;
    private String paymentMethod;
    private String returnOrderType;
    private String eventTime;
    private String consignmentNumber;
    private String awbNumber;
    private List<ReturnItemDTO> items;
    private List<ReturnShipmentDTO> shipments;
    private List<VirtualSkuDefinitionDTO> virtualSkuDefinitions;
    private List<BundledSkuDefinitionDTO> bundledSkuDefinitions;
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private Map<String, String> orderCustomAttributes;
    private List<OrderItemAttributeDTO> orderItemCustomAttributes;
    private Map<String, String> returnOrderAttributes;
}