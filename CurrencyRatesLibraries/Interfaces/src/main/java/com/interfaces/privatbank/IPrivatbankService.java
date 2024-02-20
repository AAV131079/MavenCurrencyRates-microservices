package com.interfaces.privatbank;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPrivatbankService {
    public String getCurrencyRates() throws JsonProcessingException;
}