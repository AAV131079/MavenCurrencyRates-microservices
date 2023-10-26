package com.nbuservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbuservice.dto.request.RequestDTO;
import com.nbuservice.dto.response.ResponseCurrencyRatesDTO;
import com.nbuservice.enums.CurrencyEnum;
import com.nbuservice.enums.ProviderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class NBUService implements INBUService {

    @Autowired
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    private final String SERVICE_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final String URI = "http://currency-rates-gateway/http-client/api/get/currency-rates";

    @Override
    public String getCurrencyRates() throws JsonProcessingException {
        log.info("{NBUService}");
        String uri = getUri();
        RequestDTO requestBody = getRequestBody();
        log.info(String.format("Request: %s", uri));
        log.info(String.format("Request body: %s", requestBody));
        String responseService = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(requestBody), String.class).getBody();
        String response = getResponse(responseService);
        log.info(String.format("Response: %s", response));
        return response;
    }

    private String getUri() {
        return URI;
    }

    private RequestDTO getRequestBody() {
        return new RequestDTO(SERVICE_URI);
    }

    private String getResponse(String responseService) throws JsonProcessingException {

        String modifiedResponse = String.format("{\"currencyRates\":%s}", responseService);
        ResponseCurrencyRatesDTO responseCurrencyRates = objectMapper.readValue(modifiedResponse, ResponseCurrencyRatesDTO.class);

        StringBuilder response = new StringBuilder();

        Arrays.stream(responseCurrencyRates.currencyRates)
                .filter(currencyRate -> currencyRate.getCc().equals(CurrencyEnum.EUR.name()) ||
                        currencyRate.getCc().equals(CurrencyEnum.USD.name())
                )
                .forEach(currencyRate -> {
                    if (currencyRate.getCc().equals(CurrencyEnum.EUR.name())) {
                        response.append(appendToString(CurrencyEnum.EUR, currencyRate.getRate(), currencyRate.getRate()));
                    } else if (currencyRate.getCc().equals(CurrencyEnum.USD.name())) {
                        response.append(appendToString(CurrencyEnum.USD, currencyRate.getRate(), currencyRate.getRate()));
                    }
                });

        return String.format("[%s]", response.substring(0, response.length() - 1));

    }

    private String appendToString(CurrencyEnum currency, Float buy, Float sale) {

        StringBuilder currencyRate = new StringBuilder();

        currencyRate.append(String.format("{\"providerType\":\"%s\",", ProviderEnum.NBU));
        currencyRate.append(String.format("\"currencyType\":\"%s\",", currency.toString()));
        currencyRate.append(String.format("\"buy\":\"%s\",", buy.toString()));
        currencyRate.append(String.format("\"sale\":\"%s\"},", sale.toString()));

        return currencyRate.toString();

    }

}