package com.example.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.cdp")
@EntityScan("com.cdp.domain.model")
@EnableJpaRepositories(basePackages = "com.cdp.domain.repository")
public class SpringBootMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMonitoringApplication.class, args);
    }

}
