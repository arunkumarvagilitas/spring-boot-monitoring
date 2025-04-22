package com.cdp.application.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MetricsService {
    private final MeterRegistry meterRegistry;

    // Event Processing Metrics
    public void incrementEventReceived(String eventType) {
        Counter.builder("cdp.events.received")
                .tag("event_type", eventType)
                .description("Number of events received")
                .register(meterRegistry)
                .increment();
    }

    public void incrementEventProcessed(String eventType, String status) {
        Counter.builder("cdp.events.processed")
                .tag("event_type", eventType)
                .tag("status", status)
                .description("Number of events processed")
                .register(meterRegistry)
                .increment();
    }

    public Timer.Sample startEventProcessingTimer() {
        return Timer.start(meterRegistry);
    }

    public void stopEventProcessingTimer(Timer.Sample sample, String eventType) {
        sample.stop(Timer.builder("cdp.events.processing.time")
                .tag("event_type", eventType)
                .description("Event processing time")
                .register(meterRegistry));
    }

    // Kafka Metrics
    public void incrementKafkaMessageSent(String topic) {
        Counter.builder("cdp.kafka.messages.sent")
                .tag("topic", topic)
                .description("Number of Kafka messages sent")
                .register(meterRegistry)
                .increment();
    }

    public void incrementKafkaMessageReceived(String topic) {
        Counter.builder("cdp.kafka.messages.received")
                .tag("topic", topic)
                .description("Number of Kafka messages received")
                .register(meterRegistry)
                .increment();
    }

    public void recordKafkaLatency(String topic, long latencyMs) {
        Timer.builder("cdp.kafka.latency")
                .tag("topic", topic)
                .description("Kafka message latency")
                .register(meterRegistry)
                .record(latencyMs, TimeUnit.MILLISECONDS);
    }

    // Error Metrics
    public void incrementError(String type, String errorCode) {
        Counter.builder("cdp.errors")
                .tag("type", type)
                .tag("error_code", errorCode)
                .description("Number of errors")
                .register(meterRegistry)
                .increment();
    }

    // Retry Metrics
    public void incrementRetry(String eventType) {
        Counter.builder("cdp.events.retried")
                .tag("event_type", eventType)
                .description("Number of event retries")
                .register(meterRegistry)
                .increment();
    }

    // Database Metrics
    public void recordDatabaseOperationTime(String operation, long timeMs) {
        Timer.builder("cdp.database.operation.time")
                .tag("operation", operation)
                .description("Database operation time")
                .register(meterRegistry)
                .record(timeMs, TimeUnit.MILLISECONDS);
    }

    // API Metrics
    public void recordApiLatency(String endpoint, String method, long timeMs) {
        Timer.builder("cdp.api.latency")
                .tag("endpoint", endpoint)
                .tag("method", method)
                .description("API endpoint latency")
                .register(meterRegistry)
                .record(timeMs, TimeUnit.MILLISECONDS);
    }

    public void incrementApiRequest(String endpoint, String method, int status) {
        Counter.builder("cdp.api.requests")
                .tag("endpoint", endpoint)
                .tag("method", method)
                .tag("status", String.valueOf(status))
                .description("Number of API requests")
                .register(meterRegistry)
                .increment();
    }
} 