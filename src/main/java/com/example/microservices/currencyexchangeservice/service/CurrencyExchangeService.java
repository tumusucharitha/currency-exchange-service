package com.example.microservices.currencyexchangeservice.service;

import com.example.microservices.currencyexchangeservice.CurrencyExchangeRepository;
import com.example.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    @Autowired
    public CurrencyExchangeRepository repo;

    public CurrencyExchange saveCurrency(CurrencyExchange currencyExchange){
        return repo.save(currencyExchange);
    }


}
