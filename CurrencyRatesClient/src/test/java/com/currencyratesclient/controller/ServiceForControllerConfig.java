package com.currencyratesclient.controller;

import com.currencyratesclient.service.ClientService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServiceForControllerConfig {

    @Bean
    public ClientService examService(){
        return new ClientService();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

}
