package com.interfaces.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.ExecutionException;

public interface IConsumerController {
    public void getCurrencyRatesFromProvider() throws ExecutionException, InterruptedException;
}