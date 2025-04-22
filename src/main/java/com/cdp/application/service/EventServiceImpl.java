package com.cdp.application.service;//package com.cdp.application.service;
//
//import com.cdp.adapters.out.kafka.KafkaProducerAdapter;
//import com.cdp.domain.model.ApiEvent;
//import com.cdp.domain.model.ApiEventStatus;
//import com.cdp.domain.repository.ApiEventRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.time.LocalDateTime;
//import java.util.Map;
//import java.util.UUID;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class EventServiceImpl implements EventService {
//
//    private final KafkaProducerAdapter kafkaProducer;
//    private final ApiEventRepository eventRepository;
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public ApiEvent processEvent(String eventType, Map<String, Object> payload, Map<String, String> headers, String curlCommand) {
//        try {
//            log.debug("Processing event - eventType: {}, payload: {}, headers: {}", eventType, payload, headers);
//
//            // Get current request
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = attributes != null ? attributes.getRequest() : null;
//
//            // Create new event
//            ApiEvent event = new ApiEvent();
//            event.setId(UUID.randomUUID().toString());
//            event.setTimestamp(LocalDateTime.now());
//            event.setStatus(ApiEventStatus.PENDING);
//            event.setCanRetry(true);
//            event.setRetryCount(0);
//            event.setCorrelationId(headers.getOrDefault("X-Correlation-ID", UUID.randomUUID().toString()));
//            log.debug("Created new event with ID: {}, correlationId: {}", event.getId(), event.getCorrelationId());
//
//            // Set event type
////            event.setEventType(eventType);
//
//            // Set request metadata if available
//            if (request != null) {
//                event.setRequestMethod(request.getMethod());
//                event.setRequestUrl(request.getRequestURL().toString());
//                event.setSourceIp(getClientIp(request));
//                event.setUserAgent(request.getHeader("User-Agent"));
//            }
//
//            // Convert headers to JSON
//            try {
//                event.setRequestHeaders(objectMapper.writeValueAsString(headers));
//                log.debug("Converted headers to JSON: {}", event.getRequestHeaders());
//            } catch (JsonProcessingException e) {
//                log.warn("Failed to convert headers to JSON: {}", e.getMessage());
//                event.setRequestHeaders("{}");
//            }
//
//            // Set payload
////            event.setPayload(payload);
//
//            // Set curl command
//            event.setOriginalCurlCommand(curlCommand);
//
//            // Save event first
//            log.debug("Saving event to database");
//            event = eventRepository.save(event);
//            log.info("Event saved with ID: {}", event.getId());
//
//            // Try to send to Kafka
//            try {
//                log.debug("Sending event to Kafka - topic: {}, payload: {}", eventType, payload);
//                kafkaProducer.sendEvent(eventType + "-topic", payload);
//                event.setStatus("SUCCESS");
//                event.setProcessedAt(LocalDateTime.now());
//                log.info("Event sent to Kafka successfully: {}", event.getId());
//            } catch (Exception e) {
//                log.error("Failed to send event to Kafka: {}", e.getMessage(), e);
//                event.setStatus("FAILED");
//                event.setErrorMessage(e.getMessage());
//                event.setFailureReason("Kafka send failed");
//            }
//
//            // Update event status
//            log.debug("Updating event status in database");
//            return eventRepository.save(event);
//
//        } catch (Exception e) {
//            log.error("Error processing event: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to process event: " + e.getMessage(), e);
//        }
//    }
//
//    private String getClientIp(HttpServletRequest request) {
//        String ip = request.getHeader("X-Forwarded-For");
//        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//
//    @Override
//    public ApiEvent getEventStatus(String correlationId) {
//        return eventRepository.findByCorrelationId(correlationId)
//                .orElseThrow(() -> new RuntimeException("Event not found: " + correlationId));
//    }
//
//    @Override
//    public ApiEvent retryEvent(String correlationId) {
//        ApiEvent event = eventRepository.findByCorrelationId(correlationId)
//                .orElseThrow(() -> new RuntimeException("Event not found: " + correlationId));
//
//        if (!event.getCanRetry()) {
//            throw new RuntimeException("Event cannot be retried: " + event.getFailureReason());
//        }
//
//        if (event.getRetryCount() >= 3) {
//            throw new RuntimeException("Maximum retry attempts reached");
//        }
//
//        // Reset event status
//        event.setStatus("PENDING");
//        event.setRetryCount(event.getRetryCount() + 1);
//        event.setErrorMessage(null);
//        event.setFailureReason(null);
//        event.setLastRetryAt(LocalDateTime.now());
//
//        // Save and process
//        event = eventRepository.save(event);
//        return processEvent(event.getEventType(), event.getPayload(), Map.of(), event.getOriginalCurlCommand());
//    }
//}