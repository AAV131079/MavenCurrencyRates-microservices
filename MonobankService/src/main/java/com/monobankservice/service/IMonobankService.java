package com.monobankservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IMonobankService {
    public String getCurrencyRates() throws JsonProcessingException;
}