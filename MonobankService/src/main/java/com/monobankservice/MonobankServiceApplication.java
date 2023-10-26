package com.monobankservice;

import com.monobankservice.requestfactory.CustomHttpComponentsClientHttpRequestFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class MonobankServiceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate(new CustomHttpComponentsClientHttpRequestFactory());
	}

	public static void main(String[] args) {
		SpringApplication.run(MonobankServiceApplication.class, args);
	}

}