package com.cdp.dto.return_order;
import com.cdp.dto.outward.ShipmentDetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnShipmentDTO {
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
    private List<ReturnShipmentItemDTO> shipmentItems = new ArrayList<>();
    private String locationCode;
    private String awbNumber;
    private String transporter;
    private String packageSku;
    private String deliveredAt;
    private String packedAt;
    private ShipmentDetailsDTO shipmentDetails;
}