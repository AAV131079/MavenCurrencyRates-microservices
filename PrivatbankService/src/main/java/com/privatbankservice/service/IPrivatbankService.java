package com.privatbankservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPrivatbankService {
    public String getCurrencyRates() throws JsonProcessingException;
}