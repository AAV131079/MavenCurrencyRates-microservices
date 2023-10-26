package com.currencyratesconsumer.controller;

import com.currencyratesconsumer.enums.ProviderEnum;
import com.currencyratesconsumer.service.IConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@EnableScheduling
public class ConsumerController implements IConsumerController {

    @Autowired
    private IConsumerService consumerService;

    @Override
    @Scheduled(cron = "${scheduler.cron}")
    public void getCurrencyRatesFromProvider() {
        log.info("{ConsumerController}");
        Arrays.stream(ProviderEnum.values()).toList().forEach(provider -> {
            try {
                log.info(String.format("Provider: %s", provider));
                consumerService.getCurrencyRatesFromProvider(provider.name().toLowerCase());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

}