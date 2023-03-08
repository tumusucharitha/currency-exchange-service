package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.example.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeService service;

    @GetMapping("/currencyExchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50) );
        if(currencyExchange == null){
            throw new RuntimeException("unable to find the data.");
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }

    @PostMapping("/currencyExchange/save")
    public CurrencyExchange saveCurrencyExchangeRate(@RequestBody CurrencyExchange currencyExchange){
        service.saveCurrency(currencyExchange);
        return currencyExchange;
    }
}
