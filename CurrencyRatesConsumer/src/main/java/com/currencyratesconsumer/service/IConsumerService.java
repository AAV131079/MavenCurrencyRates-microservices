package com.currencyratesconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConsumerService {
    public void getCurrencyRatesFromProvider(String provider) throws JsonProcessingException;
}