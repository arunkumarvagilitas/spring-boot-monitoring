package com.cdp.dto.outward;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentDTO {
    private String shipmentCode;
    private Long omsShipmentId;
    private String generatedInvoiceId;
    private String generatedInvoiceDate;
    private String invoiceDocumentUrl;
    private String shippingLabelDocumentUrl;
    private String externalInvoiceId;
    private String externalInvoiceDate;
    private String irn;
    private String qrCode;
    private String shipmentStatus;
    private List<ShipmentItemDTO> shipmentItems = new ArrayList<>();
    private String locationCode;
    private String awbNumber;
    private String transporter;
    private String packageSku;
    private String deliveredAt;
    private String packedAt;
    private ShipmentDetailsDTO shipmentDetails;

    public List<ShipmentItemDTO> getShipmentItems() {
        return shipmentItems != null ? shipmentItems : new ArrayList<>();
    }
}
