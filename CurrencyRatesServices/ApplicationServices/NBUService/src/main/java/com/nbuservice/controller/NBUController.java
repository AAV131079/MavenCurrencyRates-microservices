package com.nbuservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interfaces.nbu.INBUController;
import com.interfaces.nbu.INBUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/get")
public class NBUController implements INBUController {

    private final INBUService nbuService;

    @Autowired
    public NBUController(INBUService nbuService) {
        this.nbuService = nbuService;
    }

    @Override
    @GetMapping("/currency-rates")
    public String getCurrencyRates() throws JsonProcessingException {
        log.info("{NBUController::getCurrencyRates}");
        return nbuService.getCurrencyRates();
    }

}