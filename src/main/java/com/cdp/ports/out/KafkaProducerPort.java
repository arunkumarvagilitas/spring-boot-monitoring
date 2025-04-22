package com.cdp.ports.out;

import java.util.Map;

public interface KafkaProducerPort {
    void sendEvent(String eventType, Map<String, Object> payload);
} 