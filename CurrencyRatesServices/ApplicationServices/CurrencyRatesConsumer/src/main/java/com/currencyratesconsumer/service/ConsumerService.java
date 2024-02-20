package com.currencyratesconsumer.service;

import com.dto.CurrencyRatesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpers.objectmapper.ObjectMapperHelper;
import com.helpers.resttemplate.RestTemplateHelper;
import com.interfaces.consumer.IConsumerService;
import com.repository.ICurrencyRatesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@EnableAsync
@EntityScan("com.dao")
@ComponentScan({"com.helpers", "com.repository"})
public class ConsumerService implements IConsumerService {

    private final ICurrencyRatesRepository currencyRatesRepository;
    private final RestTemplateHelper restTemplateHelper;
    private final ObjectMapperHelper objectMapperHelper;

    private final String PROVIDER_URL = "http://currency-rates-gateway/currency-rates-provider/provider=%s";

    @Autowired
    public ConsumerService(ICurrencyRatesRepository currencyRatesRepository, RestTemplateHelper restTemplateHelper,
                           ObjectMapperHelper objectMapperHelper) {
        this.currencyRatesRepository = currencyRatesRepository;
        this.restTemplateHelper = restTemplateHelper;
        this.objectMapperHelper = objectMapperHelper;
    }

    @Async
    @Override
    public CompletableFuture<String> getCurrencyRatesFromProvider(String provider)
            throws JsonProcessingException {
      log.info("Request to provider: {} is started - {}, thread: {}", provider, new Date(),
              Thread.currentThread().getName());
      String url = getUrl(provider);
      log.info("Request: {}", url);
      String providerResponse = restTemplateHelper.getResponse(url);
      if (Objects.nonNull(providerResponse)) {
          log.info("Response: {}", providerResponse);
          saveToDatabase(providerResponse);
          log.info("{}. Work correct!!!", Thread.currentThread().getName());
          return CompletableFuture.completedFuture(providerResponse);
      }
      return CompletableFuture.completedFuture(null);
    }

    private String getUrl(String provider) {
        return String.format(PROVIDER_URL, provider);
    }

    void saveToDatabase(String providerResponse) throws JsonProcessingException {
        String modifiedResponse = String.format("{\"currencyRates\":%s}", providerResponse);
        Arrays.stream(((CurrencyRatesDTO) objectMapperHelper.fillDTO(modifiedResponse,
                        CurrencyRatesDTO.class)).currencyRates)
                .toList()
                .forEach(currencyRate -> {
                    currencyRatesRepository.save(currencyRate);
                    log.info("Save to database: {}", currencyRate.toString());
                });
    }

}