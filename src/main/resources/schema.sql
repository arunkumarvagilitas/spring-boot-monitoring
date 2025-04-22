DROP TABLE IF EXISTS api_event_log;

CREATE TABLE api_event_log (
    id bigInt NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event_type VARCHAR(255),
    correlation_id VARCHAR(255),
    status VARCHAR(50),
    payload TEXT,
    error_message TEXT,
    failure_reason TEXT,
    processing_notes TEXT,
    retry_count INT DEFAULT 0,
    can_retry BOOLEAN DEFAULT TRUE,
    last_retry_at TIMESTAMP,
    processed_at TIMESTAMP,
    request_method VARCHAR(10),
    request_url VARCHAR(1000),
    request_headers TEXT,
    source_ip VARCHAR(50),
    user_agent VARCHAR(255),
    original_curl_command TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
); 