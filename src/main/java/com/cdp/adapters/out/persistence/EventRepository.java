package com.cdp.adapters.out.persistence;

import com.cdp.domain.model.ApiEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<ApiEvent, String> {
    ApiEvent findByCorrelationId(String correlationId);
} 