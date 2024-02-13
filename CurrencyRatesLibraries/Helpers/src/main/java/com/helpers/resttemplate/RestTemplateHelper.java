package com.helpers.resttemplate;

import com.dto.Request.UrlRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestTemplateHelper {

    private final RestTemplate restTemplateBuilder;
    private final RestTemplate restTemplateFactory;

    private int sendCount = 1;
    private static final int SEND_COUNT = 3;

    @Autowired
    public RestTemplateHelper(RestTemplate restTemplateBuilder, RestTemplate restTemplateFactory) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.restTemplateFactory = restTemplateFactory;
    }

    public String getResponse(String url, UrlRequestDTO requestBody) throws RestClientException {
        ResponseEntity<String> response = null;
        try{
            response = restTemplateFactory.exchange(url, HttpMethod.GET, new HttpEntity<>(requestBody), String.class);
        } catch (RestClientException e) {
            log.warn("Error!!! In thread: {}, Message: {}", Thread.currentThread().getName(), e.getMessage());
        }
        assert response != null;
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Thread: {}, url: {}, status: {}", Thread.currentThread().getName(), url, response.getStatusCode());
            return response.getBody();
        } else if (response.getStatusCode().is5xxServerError() && sendCount <= SEND_COUNT) {
            log.info("Resending the request! Try: {}", sendCount);
            getResponse(url, requestBody);
            sendCount++;
        }
        return null;
    }

    public String getResponse(String url) throws RestClientException {
        String response = null;
        try{
            response = restTemplateBuilder.getForObject(url,String.class);
        } catch (RestClientException e) {
            log.warn("Error!!! In thread: {}, Message: {}", Thread.currentThread().getName(), e.getMessage());
        }
        assert response != null;
        return response;
    }

}