package com.interfaces.nbu;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface INBUService {
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException;
}