package com.interfaces.monobank;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IMonobankService {
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException;
}