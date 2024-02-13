package com.interfaces.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.CompletableFuture;

public interface IConsumerService {
    public CompletableFuture<String> getCurrencyRatesFromProvider(String provider) throws JsonProcessingException, InterruptedException;
}