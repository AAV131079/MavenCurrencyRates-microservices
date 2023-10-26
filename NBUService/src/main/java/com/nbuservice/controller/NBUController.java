package com.nbuservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbuservice.service.INBUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/get")
public class NBUController implements INBUController {

    @Autowired
    private INBUService nbuService;

    @Override
    @GetMapping("/currency-rates")
    public String getCurrencyRates() throws JsonProcessingException {
        log.info("{NBUController}");
        return nbuService.getCurrencyRates();
    }

}