package com.privatbankservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPrivatbankController {
    public String getCurrencyRates() throws JsonProcessingException;
}