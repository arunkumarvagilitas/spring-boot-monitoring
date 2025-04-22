package com.cdp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class DashboardConfig implements WebMvcConfigurer {

    private final FeatureConfig featureConfig;

    @Override
    @ConditionalOnProperty(name = "features.monitoring-dashboards.enabled", havingValue = "true")
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dashboards/**")
            .addResourceLocations("classpath:/dashboards/");
    }

    @Bean
    @ConditionalOnProperty(name = "features.monitoring-dashboards.enabled", havingValue = "true")
    public WebMvcConfigurer dashboardConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/dashboards/**")
                    .addResourceLocations("classpath:/dashboards/")
                    .setCachePeriod(3600)
                    .resourceChain(true);
            }
        };
    }
} 