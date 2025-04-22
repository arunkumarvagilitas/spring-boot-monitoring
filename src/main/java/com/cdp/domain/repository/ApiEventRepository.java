package com.cdp.domain.repository;

import com.cdp.domain.model.ApiEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiEventRepository extends JpaRepository<ApiEvent, Long> {
    //Optional<ApiEvent> findByCorrelationId(Long correlationId);
} 