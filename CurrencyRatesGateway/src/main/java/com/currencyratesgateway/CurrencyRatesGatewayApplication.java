package com.currencyratesgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyRatesGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyRatesGatewayApplication.class, args);
    }

}
