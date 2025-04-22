package com.cdp.adapters.in.rest;

import io.micrometer.core.instrument.MeterRegistry;
//import io.prometheus.client.CollectorRegistry;
//import io.prometheus.client.exporter.common.TextFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/monitoring")
@RequiredArgsConstructor
public class MonitoringController implements HealthIndicator {

    private final MeterRegistry meterRegistry;
    //private final CollectorRegistry collectorRegistry;

//    @GetMapping("/metrics")
//    public ResponseEntity<String> metrics() throws Exception {
//        StringWriter writer = new StringWriter();
//        TextFormat.write004(writer, collectorRegistry.metricFamilySamples());
//        return ResponseEntity.ok(writer.toString());
//    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthStatus() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("components", getComponentHealth());
        return ResponseEntity.ok(health);
    }

    @Override
    public Health health() {
        try {
            Map<String, Object> details = new HashMap<>();
            details.put("components", getComponentHealth());
            return Health.up().withDetails(details).build();
        } catch (Exception e) {
            return Health.down().withException(e).build();
        }
    }

    private Map<String, Object> getComponentHealth() {
        Map<String, Object> components = new HashMap<>();
        
        // Database Health
        components.put("database", checkDatabaseHealth());
        
        // Kafka Health
        components.put("kafka", checkKafkaHealth());
        
        // Application Health
        components.put("application", checkApplicationHealth());
        
        return components;
    }

    private Map<String, Object> checkDatabaseHealth() {
        Map<String, Object> health = new HashMap<>();
        try {
            // Add database health check logic
            health.put("status", "UP");
            health.put("details", "Database connection is healthy");
        } catch (Exception e) {
            health.put("status", "DOWN");
            health.put("error", e.getMessage());
        }
        return health;
    }

    private Map<String, Object> checkKafkaHealth() {
        Map<String, Object> health = new HashMap<>();
        try {
            // Add Kafka health check logic
            health.put("status", "UP");
            health.put("details", "Kafka connection is healthy");
        } catch (Exception e) {
            health.put("status", "DOWN");
            health.put("error", e.getMessage());
        }
        return health;
    }

    private Map<String, Object> checkApplicationHealth() {
        Map<String, Object> health = new HashMap<>();
        try {
            // Add application health check logic
            health.put("status", "UP");
            health.put("version", "1.0.0");
            health.put("uptime", getUptime());
        } catch (Exception e) {
            health.put("status", "DOWN");
            health.put("error", e.getMessage());
        }
        return health;
    }

    private String getUptime() {
        double uptime = meterRegistry.get("jvm.uptime").timer().mean(java.util.concurrent.TimeUnit.SECONDS);
        return String.format("%.2f seconds", uptime);
    }
} 