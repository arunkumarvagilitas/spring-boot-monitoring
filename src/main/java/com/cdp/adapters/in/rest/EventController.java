package com.cdp.adapters.in.rest;//package com.cdp.adapters.in.rest;
//
//import com.cdp.application.service.EventService;
//import com.cdp.domain.model.ApiEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/events")
//@RequiredArgsConstructor
//public class EventController {
//
//    private final EventService eventService;
//    private final ObjectMapper objectMapper;
//
//    @RequestMapping(value = "/{eventType}", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
//    public ResponseEntity<Map<String, Object>> processEvent(
//            @PathVariable String eventType,
//            @RequestBody(required = false) Map<String, Object> payload,
//            @RequestHeader Map<String, String> headers,
//            HttpServletRequest request) {
//        try {
//            log.debug("Received event request - eventType: {}, payload: {}, headers: {}", eventType, payload, headers);
//
//            // If payload is null, try to read from request body
//            if (payload == null) {
//                try {
//                    log.debug("Payload is null, attempting to read from request body");
//                    payload = objectMapper.readValue(request.getInputStream(), Map.class);
//                    log.debug("Successfully read payload from request body: {}", payload);
//                } catch (Exception e) {
//                    log.warn("Failed to read request body: {}", e.getMessage());
//                }
//            }
//
//            // Generate correlation ID from headers or create new one
//            String correlationId = headers.getOrDefault("X-Correlation-ID", java.util.UUID.randomUUID().toString());
//            log.debug("Using correlation ID: {}", correlationId);
//
//            // Construct curl command
//            StringBuilder curlCommand = new StringBuilder();
//            curlCommand.append("curl --location '").append(request.getRequestURL().toString()).append("'");
//
//            // Add headers
//            headers.forEach((key, value) -> {
//                if (!key.equalsIgnoreCase("host") &&
//                    !key.equalsIgnoreCase("content-length") &&
//                    !key.equalsIgnoreCase("connection")) {
//                    curlCommand.append(" \\\n--header '").append(key).append(": ").append(value).append("'");
//                }
//            });
//
//            // Add payload if present
//            if (payload != null) {
//                try {
//                    String jsonPayload = objectMapper.writeValueAsString(payload);
//                    curlCommand.append(" \\\n--data-raw '").append(jsonPayload).append("'");
//                } catch (Exception e) {
//                    log.warn("Failed to convert payload to JSON: {}", e.getMessage());
//                }
//            }
//
//            // Process event
//            ApiEvent event = eventService.processEvent(eventType, payload, headers, curlCommand.toString());
//
//            // Return response
//            return ResponseEntity.ok(Map.of(
//                "id", event.getId(),
//                "status", event.getStatus(),
//                "retryCount", event.getRetryCount(),
//                "correlationId", event.getCorrelationId()
//            ));
//
//        } catch (Exception e) {
//            log.error("Failed to process event: {}", e.getMessage(), e);
//            return ResponseEntity.internalServerError().body(Map.of(
//                "error", "Failed to process event",
//                "message", e.getMessage()
//            ));
//        }
//    }
//
//    @GetMapping("/status/{correlationId}")
//    public ResponseEntity<Map<String, Object>> getEventStatus(@PathVariable String correlationId) {
//        try {
//            ApiEvent event = eventService.getEventStatus(correlationId);
//            return ResponseEntity.ok(Map.of(
//                "id", event.getId(),
//                "eventType", event.getEventType(),
//                "status", event.getStatus(),
//                "retryCount", event.getRetryCount(),
//                "correlationId", event.getCorrelationId(),
//                "timestamp", event.getTimestamp(),
//                "processedAt", event.getProcessedAt(),
//                "canRetry", event.getCanRetry(),
//                "errorMessage", event.getErrorMessage() != null ? event.getErrorMessage() : "",
//                "originalCurlCommand", event.getOriginalCurlCommand() != null ? event.getOriginalCurlCommand() : ""
//            ));
//        } catch (Exception e) {
//            log.error("Failed to get event status: {}", e.getMessage(), e);
//            return ResponseEntity.internalServerError().body(Map.of(
//                "error", "Failed to get event status",
//                "message", e.getMessage()
//            ));
//        }
//    }
//
//    @PostMapping("/retry/{correlationId}")
//    public ResponseEntity<Map<String, Object>> retryEvent(@PathVariable String correlationId) {
//        try {
//            ApiEvent event = eventService.retryEvent(correlationId);
//            return ResponseEntity.ok(Map.of(
//                "id", event.getId(),
//                "status", event.getStatus(),
//                "retryCount", event.getRetryCount(),
//                "correlationId", event.getCorrelationId()
//            ));
//        } catch (Exception e) {
//            log.error("Failed to retry event: {}", e.getMessage(), e);
//            return ResponseEntity.internalServerError().body(Map.of(
//                "error", "Failed to retry event",
//                "message", e.getMessage()
//            ));
//        }
//    }
//}