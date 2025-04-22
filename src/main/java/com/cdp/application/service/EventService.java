package com.cdp.application.service;

import com.cdp.domain.model.ApiEvent;

import java.util.Map;

public interface EventService {
    ApiEvent processEvent(String eventType, Map<String, Object> payload, Map<String, String> headers, String curlCommand);
    ApiEvent getEventStatus(String correlationId);
    ApiEvent retryEvent(String correlationId);
} 