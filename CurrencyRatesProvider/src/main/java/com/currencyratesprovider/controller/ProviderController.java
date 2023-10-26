package com.currencyratesprovider.controller;

import com.currencyratesprovider.service.IProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProviderController implements IProviderController {

    @Autowired
    private IProviderService providerService;

    @Override
    @GetMapping("/provider={provider}")
    public String getProviderResponce(@PathVariable String provider) {
        log.info("{ProviderController}");
        log.info(String.format("Provider = %s", provider));
        return providerService.getProviderResponse(provider);
    }

}