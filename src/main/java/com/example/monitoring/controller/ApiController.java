package com.example.monitoring.controller;

import com.example.monitoring.service.KafkaProducerService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final KafkaProducerService kafkaProducerService;

    public ApiController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/hello")
    public String hello() {
        logger.info("Hello endpoint called");
        kafkaProducerService.sendMessage("Hello endpoint was called");
        return "Hello from Spring Boot Microservice!";
    }

    @GetMapping("/random")
    public String random() {
        logger.info("Random endpoint called");
        kafkaProducerService.sendMessage("Random endpoint was called");
        return "Random number: " + (int)(Math.random() * 100);
    }
}