package com.example.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @Retry(name="default", fallbackMethod = "hardCodedResponse")
    public String sampleApi(){
        logger.info("sample api called.");
        ResponseEntity<String> temp = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return temp.getBody();
    }

    public String hardCodedResponse(Exception ex){
        return "Fallback-response";
    }
}
