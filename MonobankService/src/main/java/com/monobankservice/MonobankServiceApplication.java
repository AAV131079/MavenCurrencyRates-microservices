package com.monobankservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MonobankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonobankServiceApplication.class, args);
	}

}
