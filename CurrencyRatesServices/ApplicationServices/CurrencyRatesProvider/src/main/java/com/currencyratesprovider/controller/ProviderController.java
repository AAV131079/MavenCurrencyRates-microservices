package com.currencyratesprovider.controller;

import com.interfaces.provider.IProviderController;
import com.interfaces.provider.IProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProviderController implements IProviderController {

    private final IProviderService providerService;

    @Autowired
    public ProviderController(IProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    @GetMapping("/provider={provider}")
    public String getProviderResponse(@PathVariable String provider) throws InterruptedException {
        log.info("{ProviderController::getProviderResponse}");
        log.info("Provider = {}", provider);
        return providerService.getProviderResponse(provider);
    }

}