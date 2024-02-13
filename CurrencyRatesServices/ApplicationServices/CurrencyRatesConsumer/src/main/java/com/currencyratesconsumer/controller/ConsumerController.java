package com.currencyratesconsumer.controller;

import com.enumerators.ProviderEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.interfaces.consumer.IConsumerController;
import com.interfaces.consumer.IConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@EnableScheduling
public class ConsumerController implements IConsumerController {

    private final IConsumerService consumerService;

    @Autowired
    public ConsumerController(IConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @Override
    @Scheduled(cron = "0 0,5,10,15,20,25,30,35,40,45,50,55 * * * *", zone="Europe/Kiev")
    public void getCurrencyRatesFromProvider() throws ExecutionException, InterruptedException {
        log.info("{ConsumerController::getCurrencyRatesFromProvider}");
        List<CompletableFuture<String>> completableFutures = Arrays.stream(ProviderEnum.values())
                .map(provider -> {
                    try {
                        return consumerService.getCurrencyRatesFromProvider(provider.name().toLowerCase());
                    } catch (JsonProcessingException | InterruptedException e) {
                        log.info("Error!!! In thread: {}, message: {}",Thread.currentThread().getName(), e.getMessage());
                    }
                    return null;
                }).toList();
        CompletableFuture<Void> allOffCompletableFutures = CompletableFuture.allOf(
                completableFutures.toArray(new CompletableFuture[0])
        );
        CompletableFuture<List<String>> allOffCompletableFuturesResults = allOffCompletableFutures.thenApply(
                v -> completableFutures.stream()
                        .map(completableFuture -> {
                            try {
                                return completableFuture.join();
                            } catch (Exception e) {
                                log.info("Error!!! In thread: {}, message: {}", Thread.currentThread().getName(), e.getMessage());
                            }
                            return null;
                        })
                        .collect(Collectors.toList())
        );
        log.info("Providers work results:");
        allOffCompletableFuturesResults.get().forEach(log::info);
    }

}