package com.cdp.adapters.out.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerAdapter {

    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    public void sendEvent(String topic, Map<String, Object> payload) {
        try {
            log.info("Sending event to topic: {}", topic);
            kafkaTemplate.send(topic, payload);
            log.info("Event sent successfully to topic: {}", topic);
        } catch (Exception e) {
            log.error("Failed to send event to Kafka: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send event to Kafka: " + e.getMessage(), e);
        }
    }
}