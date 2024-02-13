package com.interfaces.privatbank;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPrivatbankController {
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException;
}