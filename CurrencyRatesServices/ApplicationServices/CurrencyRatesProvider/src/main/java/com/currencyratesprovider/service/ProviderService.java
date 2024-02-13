package com.currencyratesprovider.service;

import com.helpers.resttemplate.RestTemplateHelper;
import com.interfaces.provider.IProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ComponentScan({"com.helpers"})
public class ProviderService implements IProviderService {

    private final RestTemplateHelper restTemplateHelper;

    private final String SERVICE_URI = "http://currency-rates-gateway/%s-service/get/currency-rates";

    @Autowired
    public ProviderService(RestTemplateHelper restTemplateHelper) {
        this.restTemplateHelper = restTemplateHelper;
    }

    @Override
    public String getProviderResponse(String provider) throws InterruptedException {
        log.info("{ProviderService::getProviderResponse}");
        String url = getUrl(provider);
        log.info("Request: {}", url);
        String providerResponse = restTemplateHelper.getResponse(url);
        assert providerResponse != null;
        log.info("Response: {}", providerResponse);
        return providerResponse;
    }

    private String getUrl(String provider) {
        return String.format(SERVICE_URI, provider);
    }

}