package com.interfaces.nbu;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface INBUController {
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException;
}