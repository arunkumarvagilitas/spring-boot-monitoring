package com.cdp.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MonitoringConfig {

    private final FeatureConfig featureConfig;

    @Bean
    @Conditional(PrometheusEnabledCondition.class)
    public MeterRegistry prometheusMeterRegistry() {
        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }

    @Bean
    @Conditional(PrometheusEnabledCondition.class)
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    @Conditional(PrometheusEnabledCondition.class)
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
            .commonTags("application", "cdp");
    }

    public static class PrometheusEnabledCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            boolean enabled = Boolean.parseBoolean(context.getEnvironment()
                .getProperty("features.monitoring.prometheus.enabled", "false"));
            log.debug("Prometheus monitoring enabled: {}", enabled);
            return enabled;
        }
    }

    public static class GrafanaEnabledCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            boolean enabled = Boolean.parseBoolean(context.getEnvironment()
                .getProperty("features.monitoring.grafana.enabled", "false"));
            log.debug("Grafana monitoring enabled: {}", enabled);
            return enabled;
        }
    }
} 