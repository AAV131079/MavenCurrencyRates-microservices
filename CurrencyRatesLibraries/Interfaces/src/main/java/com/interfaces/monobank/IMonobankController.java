package com.interfaces.monobank;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IMonobankController {
    public String getCurrencyRates() throws JsonProcessingException;
}