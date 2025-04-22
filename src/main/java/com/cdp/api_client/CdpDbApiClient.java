package com.cdp.api_client;

import com.cdp.dto.outward.OrderDto;
import com.cdp.dto.pack_order.PackOrderRequestDTO;
import com.cdp.dto.return_order.ReturnOrderDTO;
import com.cdp.exception.ApiException;
import com.cdp.utils.Constant;
import com.cdp.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CdpDbApiClient {

    @Value("${cdp.java.basUrl}")
    private String baseUrl;

    @Value("${cdp.java.authHeader}")
    private String authHeader;

    public OrderDto saveOutwardOrder(String requestJSON) throws ApiException {
        return Utils.makePostApiCall("%s/%s".formatted(baseUrl, Constant.CDP_DB_OUTWARD_ORDER_URL), requestJSON, OrderDto.class, authHeader);
    }

    public PackOrderRequestDTO savePackOrder(String requestJSON) throws ApiException {
        return Utils.makePostApiCall("%s/%s".formatted(baseUrl, Constant.CDP_DB_PACK_ORDER_URL), requestJSON, PackOrderRequestDTO.class, authHeader);
    }

    public ReturnOrderDTO saveReturnOrder(String requestJSON) throws ApiException {
        return Utils.makePostApiCall("%s/%s".formatted(baseUrl, Constant.CDP_DB_RETURN_ORDER_URL), requestJSON, ReturnOrderDTO.class, authHeader);
    }

    public OrderDto getOutwardOrderById(Long id) throws ApiException {
        return Utils.makeGetApiCall("%s/%s/%d".formatted(baseUrl, Constant.CDP_DB_OUTWARD_ORDER_URL, id), OrderDto.class, authHeader);
    }

    public PackOrderRequestDTO getPackOrder(Long id) throws ApiException{
        return Utils.makeGetApiCall("%s/%s/%d".formatted(baseUrl, Constant.CDP_DB_PACK_ORDER_URL, id), PackOrderRequestDTO.class, authHeader);
    }

    public ReturnOrderDTO getReturnOrder(Long id) throws ApiException {
        return Utils.makeGetApiCall("%s/%s/%d".formatted(baseUrl, Constant.CDP_DB_RETURN_ORDER_URL, id), ReturnOrderDTO.class, authHeader);
    }
}
