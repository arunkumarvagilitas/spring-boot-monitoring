package com.cdp.dto.pack_order;

import com.cdp.dto.outward.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class PackOrderRequestDTO {

    private Long id;

    @JsonProperty("orderCode")
    private String orderCode;

    @JsonProperty("locationCode")
    private String locationCode;

    @JsonProperty("shipmentId")
    private Integer shipmentId;

    @JsonProperty("packageSku")
    private String packageSku;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("fulfillmentType")
    private String fulfillmentType;

    @JsonProperty("dimensions")
    private Dimensions dimensions;

    @JsonProperty("invoice")
    private Invoice invoice;

    @JsonProperty("packboxDetailsList")
    private List<PackboxDetails> packboxDetailsList;

    @JsonProperty("shipmentItems")
    private List<ShipmentItem> shipmentItems;

    @JsonProperty("orderCustomAttributes")
    private OrderCustomAttributes orderCustomAttributes;

    // Inner DTO classes
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Dimensions {
        private Double length;
        private Double breadth;
        private Double height;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Invoice {
        private AddressDTO fromAddress;
        private AddressDTO shippingAddress;
        private AddressDTO billingAddress;
        private String orderTime;
        private String channelName;
        private String fromPartyName;
        private String toPartyName;
        private String fromTIN;
        private String toTIN;
        private String panNo;
        private List<InvoiceItem> invoiceItems;
    }




    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InvoiceItem {
        private Double actualSellingPricePerUnit;
        private Double actualSellingPriceTotal;
        private String channelSkuCode;
        private String itemName;
        private Integer quantity;
        private String taxRule;
        private String hsnCode;
        private String vendorSku;
        private Double mrp;
        private String brand;
        private String styleCode;
        private String color;
        private String size;
        private String category;
        private String imageUrl;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PackboxDetails {
        private Integer boxId;
        private String boxCode;
        private Double length;
        private Double breadth;
        private Double height;
        private Double weight;
        private Double volWeight;
        private String boxSkuId;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ShipmentItem {
        private String channelSkuCode;
        private String orderItemCode;
        private Integer quantity;
        private List<String> itemCodes;
        private List<String> externalSerialCodes;
        private SkuDimension skuDimension;
        private OrderItemCustomAttributes orderItemCustomAttributes;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SkuDimension {
        private Integer length;
        private Integer breadth;
        private Integer height;
        private Integer weight;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OrderItemCustomAttributes {
        private String attribute1;
        private String attribute2;
        private String attribute3;
        private String attribute4;
        private String attribute5;
        private String attribute6;
        private String attribute7;
        private String attribute8;
        private String attribute9;
        private String attribute10;
        private ChannelMetadata channelMetadata;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ChannelMetadata {
        private Double totalCashOnDeliveryFee;
        private String department;
        private String paymentMethod;
        private String status;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OrderCustomAttributes {
        private String attribute1;
        private String attribute2;
        private String attribute3;
        private String attribute4;
        private String attribute5;
        private String attribute6;
        private String attribute7;
        private String attribute8;
        private String attribute9;
        private String attribute10;
        private ChannelMetadata channelMetadata;
        private String currency;
    }
}