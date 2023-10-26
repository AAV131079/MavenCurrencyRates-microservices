package com.monobankservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monobankservice.dto.request.RequestDTO;
import com.monobankservice.dto.response.ResponseCurrencyRatesDTO;
import com.monobankservice.enums.CurrencyEnum;
import com.monobankservice.enums.ProviderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class MonobankService implements IMonobankService {

    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICE_URI = "https://api.monobank.ua/bank/currency";
    private final String URI = "http://currency-rates-gateway/http-client/api/get/currency-rates";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getCurrencyRates() throws JsonProcessingException {
        log.info("{MonobankService}");
        String uri = getUri();
        RequestDTO requestBody = getRequestBody();
        log.info(String.format("Request: %s", uri));
        log.info(String.format("Request body: %s", requestBody.toString()));
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

        String modifiedResponse = String.format("{\"currencyRates\": %s}", responseService);
        ResponseCurrencyRatesDTO responseCurrencyRates = objectMapper.readValue(modifiedResponse, ResponseCurrencyRatesDTO.class);

        StringBuilder response = new StringBuilder();

        Arrays.stream(responseCurrencyRates.getCurrencyRates())
                .filter(currencyRate -> currencyRate.getCurrencyCodeA() == 840 || currencyRate.getCurrencyCodeA() == 978 &&
                                        currencyRate.getCurrencyCodeB() == 980
                )
                .forEach(currencyRate -> {
                    if (currencyRate.getCurrencyCodeA() == 840 && currencyRate.getCurrencyCodeB() == 980) {
                        response.append(appendToString(CurrencyEnum.USD, currencyRate.getRateBuy(), currencyRate.getRateSell()));
                    } else if (currencyRate.getCurrencyCodeA() == 978 && currencyRate.getCurrencyCodeB() == 980) {
                        response.append(appendToString(CurrencyEnum.EUR, currencyRate.getRateBuy(), currencyRate.getRateSell()));
                    }
                });

        return String.format("[%s]", response.substring(0, response.length() - 1));

    }

    private String appendToString(CurrencyEnum currency, Float buy, Float sale) {

        StringBuilder currencyRate = new StringBuilder();

        currencyRate.append(String.format("{\"providerType\":\"%s\",", ProviderEnum.MONOBANK));
        currencyRate.append(String.format("\"currencyType\":\"%s\",", currency.toString()));
        currencyRate.append(String.format("\"buy\":\"%s\",", buy.toString()));
        currencyRate.append(String.format("\"sale\":\"%s\"},", sale.toString()));

        return currencyRate.toString();

    }

}