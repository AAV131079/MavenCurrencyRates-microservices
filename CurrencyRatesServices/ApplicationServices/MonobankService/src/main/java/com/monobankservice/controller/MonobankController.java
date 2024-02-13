package com.monobankservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interfaces.monobank.IMonobankController;
import com.interfaces.monobank.IMonobankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/get")
public class MonobankController implements IMonobankController {

    private final IMonobankService monobankService;

    @Autowired
    public MonobankController(IMonobankService monobankService) {
        this.monobankService = monobankService;
    }

    @Override
    @GetMapping("/currency-rates")
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException {
        log.info("{MonobankController::getCurrencyRates}");
        return monobankService.getCurrencyRates();
    }

}