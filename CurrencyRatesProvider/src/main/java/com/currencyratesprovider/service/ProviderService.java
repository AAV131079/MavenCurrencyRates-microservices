package com.currencyratesprovider.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ProviderService implements IProviderService {

    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICE_URI = "http://currency-rates-gateway/%s-service/get/currency-rates";

    @Override
    public String getProviderResponse(String provider) {
        log.info("{ProviderService}");
        String url = getUrl(provider);
        log.info(String.format("Request: %s", url));
        String providerResponse = restTemplate.getForObject(url, String.class);
        log.info(String.format("Response: %s", providerResponse));
        return providerResponse;
    }

    private String getUrl(String provider) {
        return String.format(SERVICE_URI, provider);
    }

}