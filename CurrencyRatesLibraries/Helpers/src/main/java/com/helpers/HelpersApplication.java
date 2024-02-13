package com.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpers.requestfactory.CustomHttpComponentsClientHttpRequestFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HelpersApplication {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplateBuilder(@NotNull RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplateFactory() {
        return new RestTemplate(new CustomHttpComponentsClientHttpRequestFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(HelpersApplication.class, args);
    }

}