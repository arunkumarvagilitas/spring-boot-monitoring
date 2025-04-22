package com.cdp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
@Entity
@Table(name = "api_event_log")
public class ApiEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type", length = 50)
    private ApiEventType eventType;

    @Column(name = "correlation_id", length = 36)
    private Long correlationId;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ApiEventStatus status;

    @Column(columnDefinition = "text")
    private String payload;

    @Column(name = "error_message", columnDefinition = "text")
    private String errorMessage;

    @Column(name = "failure_reason", columnDefinition = "text")
    private String failureReason;

    @Column(name = "processing_notes", columnDefinition = "text")
    private String processingNotes;

    @Column(name = "retry_count")
    private Integer retryCount = 0;

    @Column(name = "can_retry")
    private Boolean canRetry = true;

    @Column(name = "last_retry_at")
    private LocalDateTime lastRetryAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Column(name = "request_method", length = 10)
    private String requestMethod;

    @Column(name = "request_url", length = 1000)
    private String requestUrl;

    @Column(name = "request_headers", columnDefinition = "text")
    private String requestHeaders;

    @Column(name = "source_ip", length = 50)
    private String sourceIp;

    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Column(name = "original_curl_command", columnDefinition = "text")
    private String originalCurlCommand;

    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime timestamp;

    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
        if (status == null) {
            status = ApiEventStatus.PENDING;
        }
        if (retryCount == null) {
            retryCount = 0;
        }
        if (canRetry == null) {
            canRetry = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 