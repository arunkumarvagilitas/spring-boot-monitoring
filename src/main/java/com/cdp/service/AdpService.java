package com.cdp.service;

import com.cdp.adapters.out.kafka.KafkaProducerAdapter;
import com.cdp.api_client.CdpDbApiClient;
import com.cdp.domain.model.ApiEvent;
import com.cdp.domain.model.ApiEventStatus;
import com.cdp.domain.model.KafkaTopics;
import com.cdp.domain.repository.ApiEventRepository;
import com.cdp.dto.outward.OrderDto;
import com.cdp.dto.pack_order.PackOrderRequestDTO;
import com.cdp.dto.return_order.ReturnOrderDTO;
import com.cdp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdpService {

    @Autowired
    private CdpDbApiClient cdpDbApiClient;

    @Autowired
    private ApiEventRepository apiEventRepository;

    @Autowired
    private KafkaProducerAdapter kafkaProducerAdapter;

    public void createOutwardOrder(String requestJSON, ApiEvent apiEvent) {
        
        try{
            OrderDto orderDto = cdpDbApiClient.saveOutwardOrder(requestJSON);
            apiEvent.setCorrelationId(orderDto.getOrderId());
            apiEvent.setStatus(ApiEventStatus.DB_SUCCESS);
            sendMessage(apiEvent.getId(), KafkaTopics.OUTWARD_ORDER);
        } catch (Exception e){
            apiEvent.setStatus(ApiEventStatus.DB_FAILED);
            apiEvent.setErrorMessage(e.getMessage());
        }
        apiEventRepository.save(apiEvent);
    }

    public void packOrder(String requestJSON, ApiEvent apiEvent) {
    
        try{
            PackOrderRequestDTO packOrderRequestDTO = cdpDbApiClient.savePackOrder(requestJSON);
            apiEvent.setCorrelationId(packOrderRequestDTO.getId());
            apiEvent.setStatus(ApiEventStatus.DB_SUCCESS);
            sendMessage(apiEvent.getId(), KafkaTopics.PACK_ORDER);
        } catch (Exception e){
            apiEvent.setStatus(ApiEventStatus.DB_FAILED);
            apiEvent.setErrorMessage(e.getMessage());
        }
        apiEventRepository.save(apiEvent);
    }

    public void returnOrder(String requestJSON, ApiEvent apiEvent) {

        try{
            ReturnOrderDTO returnOrderDTO = cdpDbApiClient.saveReturnOrder(requestJSON);
            apiEvent.setCorrelationId(returnOrderDTO.getId());
            apiEvent.setStatus(ApiEventStatus.DB_SUCCESS);
            sendMessage(apiEvent.getId(), KafkaTopics.RETURN_ORDER);
        } catch (Exception e){
            apiEvent.setStatus(ApiEventStatus.DB_FAILED);
            apiEvent.setErrorMessage(e.getMessage());
        }
        apiEventRepository.save(apiEvent);
    }

    private void sendMessage(Long id, String topic) {
        kafkaProducerAdapter.sendEvent(topic, Map.of(Constant.API_EVENT_ID, id));
    }
}
