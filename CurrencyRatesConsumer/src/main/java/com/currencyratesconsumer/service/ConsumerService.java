package com.currencyratesconsumer.service;

import com.currencyratesconsumer.dto.CurrencyRatesDTO;
import com.currencyratesconsumer.repository.IConsumerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
//@Async
public class ConsumerService implements IConsumerService {

    @Autowired
    private IConsumerRepository consumerRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final String PROVIDER_URL = "http://currency-rates-gateway/currency-rates-provider/provider=%s";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getCurrencyRatesFromProvider(String provider) throws JsonProcessingException {
      String url = getUrl(provider);
      log.info(String.format("Request: %s", url));
      String providerResponse = restTemplate.getForObject(url, String.class);
      log.info(String.format("Response: %s", providerResponse));
      saveToDatabase(providerResponse);
    }

    private String getUrl(String provider) {
        return String.format(PROVIDER_URL, provider);
    }

    private void saveToDatabase(String providerResponse) throws JsonProcessingException {

        String modifiedResponse = String.format("{\"currencyRates\":%s}", providerResponse);
        CurrencyRatesDTO currencyRates = objectMapper.readValue(modifiedResponse, CurrencyRatesDTO.class);

        Arrays.stream(currencyRates.currencyRates).toList()
                .forEach(currencyRate -> {
                    log.info(String.format("Save to database: %s", currencyRate.toString()));
                    consumerRepository.save(currencyRate);
                });

    }

}