package com.nbuservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface INBUService {
    public String getCurrencyRates() throws JsonProcessingException;
}