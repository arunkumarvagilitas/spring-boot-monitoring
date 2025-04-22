package com.cdp.api_client;

import com.cdp.dto.outward.OrderDto;
import com.cdp.dto.pack_order.DeliveryNote;
import com.cdp.dto.pack_order.PackOrderRequestDTO;
import com.cdp.dto.return_order.ReturnOrderDTO;
import com.cdp.exception.ApiException;
import com.cdp.utils.Constant;
import com.cdp.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CdpErpApiClient {

    @Value("${cdp.erp.baseUrl}")
    private String baseUrl;

    public SalesInvoice createSalesInvoice(OrderDto orderDto) throws ApiException {
        return Utils.makePostApiCall("%s/%s".formatted(baseUrl, Constant.CDP_ERP_B2C_INVOICE_URL), orderDto, SalesInvoice.class, null);
    }

    public DeliveryNote createDeliveryNote(PackOrderRequestDTO packOrderRequestDTO) throws ApiException {
        return Utils.makePostApiCall("%s/%s".formatted(baseUrl, Constant.CDP_ERP_B2B_DELIVERY_NOTE_URL), packOrderRequestDTO, DeliveryNote.class, null);
    }

    public SalesInvoice createCreditNote(ReturnOrderDTO returnOrder) throws ApiException {
        return Utils.makePostApiCall("%s/%s".formatted(baseUrl, Constant.CDP_ERP_RETURN_INVOICE_URL), returnOrder, SalesInvoice.class, null);
    }
}
