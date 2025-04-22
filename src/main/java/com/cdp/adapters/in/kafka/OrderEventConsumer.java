package com.cdp.adapters.in.kafka;

import com.cdp.api_client.CdpDbApiClient;
import com.cdp.api_client.CdpErpApiClient;
import com.cdp.api_client.SalesInvoice;
import com.cdp.domain.model.ApiEvent;
import com.cdp.domain.model.ApiEventStatus;
import com.cdp.domain.model.KafkaTopics;
import com.cdp.domain.repository.ApiEventRepository;
import com.cdp.dto.outward.OrderDto;
import com.cdp.dto.pack_order.DeliveryNote;
import com.cdp.dto.pack_order.PackOrderRequestDTO;
import com.cdp.dto.return_order.ReturnOrderDTO;
import com.cdp.exception.ApiException;
import com.cdp.utils.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CdpDbApiClient cdpDbApiClient;

    @Autowired
    private CdpErpApiClient cdpErpApiClient;

    @Autowired
    private ApiEventRepository apiEventRepository;

    @KafkaListener(topics = KafkaTopics.OUTWARD_ORDER, groupId = "cdp-group")
    public void handleOutwardOrderEvent(Map<String, Object> orderEvent) throws ApiException, JsonProcessingException {
        log.info("Received outward order event: {}", orderEvent);

        ApiEvent apiEvent = getApiEvent(orderEvent);
//        OrderDto orderDto = cdpDbApiClient.getOutwardOrderById(apiEvent.getCorrelationId());
        OrderDto orderDto = new ObjectMapper().readValue(apiEvent.getPayload(), OrderDto.class);
        log.info("Order: {}", orderDto);

        try{
            SalesInvoice salesInvoice = cdpErpApiClient.createSalesInvoice(orderDto);
            log.info("Sales invoice: {}", salesInvoice);
        }catch(Exception e){
            e.printStackTrace();
            apiEvent.setStatus(ApiEventStatus.ERP_FAILED);
            apiEvent.setErrorMessage(e.getMessage());
        }
        apiEventRepository.save(apiEvent);

    }

    @KafkaListener(topics = KafkaTopics.PACK_ORDER, groupId = "cdp-group")
    public void handlePackOrderEvent(Map<String, Object> orderEvent) throws ApiException, JsonProcessingException {
        log.info("Received pack order event: {}", orderEvent);

        ApiEvent apiEvent = getApiEvent(orderEvent);
//        PackOrderRequestDTO packOrder = cdpDbApiClient.getPackOrder(apiEvent.getCorrelationId());
        PackOrderRequestDTO packOrder = new ObjectMapper().readValue(apiEvent.getPayload(), PackOrderRequestDTO.class);
        log.info("Order: {}", packOrder);

        try {
            DeliveryNote deliveryNote = cdpErpApiClient.createDeliveryNote(packOrder);
            apiEvent.setStatus(ApiEventStatus.ERP_SUCCESS);
            log.info("Delivery note: {}", deliveryNote);
        } catch(Exception e) {
            e.printStackTrace();
            apiEvent.setStatus(ApiEventStatus.ERP_FAILED);
            apiEvent.setErrorMessage(e.getMessage());
        }
        apiEventRepository.save(apiEvent);

    }

    @KafkaListener(topics = KafkaTopics.RETURN_ORDER, groupId = "cdp-group")
    public void handleReturnOrderEvent(Map<String, Object> orderEvent) throws ApiException, JsonProcessingException {
        log.info("Received return order event: {}", orderEvent);

        ApiEvent apiEvent = getApiEvent(orderEvent);
        ReturnOrderDTO returnOrder = new ObjectMapper().readValue(apiEvent.getPayload(), ReturnOrderDTO.class);
//        ReturnOrderDTO returnOrder = cdpDbApiClient.getReturnOrder(apiEvent.getCorrelationId());
        try {
            apiEvent.setStatus(ApiEventStatus.ERP_SUCCESS);
            SalesInvoice salesInvoice = cdpErpApiClient.createCreditNote(returnOrder);
            log.info("Credit Note: {}", salesInvoice);
        } catch(Exception e) {
            e.printStackTrace();
            apiEvent.setStatus(ApiEventStatus.ERP_FAILED);
            apiEvent.setErrorMessage(e.getMessage());
        }
        apiEventRepository.save(apiEvent);
    }

    private ApiEvent getApiEvent(Map<String, Object> orderEvent) throws ApiException {
        Long apiEventId = Long.parseLong(orderEvent.get(Constant.API_EVENT_ID).toString());

        Optional<ApiEvent> apiEvent = apiEventRepository.findById(apiEventId);
        if(apiEvent.isEmpty())
            throw new ApiException("ApiEvent not found", HttpStatus.INTERNAL_SERVER_ERROR);

        return apiEvent.get();
    }
}