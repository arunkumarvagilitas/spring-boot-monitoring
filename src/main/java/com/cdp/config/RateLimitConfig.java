package com.cdp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RateLimitConfig implements WebMvcConfigurer {

    private final FeatureConfig featureConfig;

    @Override
    @ConditionalOnProperty(name = "features.rate-limiting.enabled", havingValue = "true")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimitInterceptor());
    }

    private static class RateLimitInterceptor implements HandlerInterceptor {
        private final Map<String, RequestCounter> requestCounts = new ConcurrentHashMap<>();
        private static final int MAX_REQUESTS = 100;
        private static final Duration WINDOW = Duration.ofMinutes(1);

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            String clientId = getClientId(request);
            RequestCounter counter = requestCounts.computeIfAbsent(clientId, k -> new RequestCounter());

            if (counter.tryIncrement()) {
                return true;
            } else {
                response.setStatus(429); // Too Many Requests
                response.setHeader("X-RateLimit-Limit", String.valueOf(MAX_REQUESTS));
                response.setHeader("X-RateLimit-Remaining", "0");
                response.setHeader("X-RateLimit-Reset", String.valueOf(counter.getResetTime()));
                log.warn("Rate limit exceeded for client: {}", clientId);
                return false;
            }
        }

        private String getClientId(HttpServletRequest request) {
            String clientIp = request.getRemoteAddr();
            String xForwardedFor = request.getHeader("X-Forwarded-For");
            if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
                clientIp = xForwardedFor.split(",")[0].trim();
            }
            return clientIp;
        }

        private static class RequestCounter {
            private final AtomicInteger count = new AtomicInteger(0);
            private volatile long windowStart = System.currentTimeMillis();

            public boolean tryIncrement() {
                long now = System.currentTimeMillis();
                if (now - windowStart > WINDOW.toMillis()) {
                    synchronized (this) {
                        if (now - windowStart > WINDOW.toMillis()) {
                            count.set(0);
                            windowStart = now;
                        }
                    }
                }

                int currentCount = count.incrementAndGet();
                if (currentCount > MAX_REQUESTS) {
                    count.decrementAndGet();
                    return false;
                }
                return true;
            }

            public long getResetTime() {
                return windowStart + WINDOW.toMillis();
            }
        }
    }
} 