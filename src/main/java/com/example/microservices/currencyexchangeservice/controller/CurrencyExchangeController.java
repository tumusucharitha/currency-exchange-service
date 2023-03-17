package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.CurrencyExchangeRepository;
import com.example.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.example.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import org.slf4j.Logger;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeService service;
    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        //INFO [currency-exchange, 6a4f4f589856ec1f9282d8027098186f, ca00239ab2de3270] 56149 ---
        logger.info("retrieveExchangeValue called for " + from + " " + to);
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
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
