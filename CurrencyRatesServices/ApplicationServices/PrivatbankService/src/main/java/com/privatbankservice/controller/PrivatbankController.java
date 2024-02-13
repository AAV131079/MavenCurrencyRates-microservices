package com.privatbankservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interfaces.privatbank.IPrivatbankController;
import com.interfaces.privatbank.IPrivatbankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/get")
public class PrivatbankController implements IPrivatbankController {

    private final IPrivatbankService privatbankService;

    @Autowired
    public PrivatbankController(IPrivatbankService privatbankService) {
        this.privatbankService = privatbankService;
    }

    @Override
    @GetMapping("/currency-rates")
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException {
        log.info("{PrivatbankController::getCurrencyRates}");
        return privatbankService.getCurrencyRates();
    }

}