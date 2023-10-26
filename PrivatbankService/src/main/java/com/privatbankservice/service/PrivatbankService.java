package com.privatbankservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.privatbankservice.dto.CurrencyRate;
import com.privatbankservice.dto.request.RequestDTO;
import com.privatbankservice.dto.response.ResponseCurrencyRatesDTO;
import com.privatbankservice.enums.CurrencyEnum;
import com.privatbankservice.enums.ProviderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class PrivatbankService implements IPrivatbankService {

    @Autowired
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    private final String SERVICE_URI = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
    private final String URI = "http://currency-rates-gateway/http-client/api/get/currency-rates";

    @Override
    public String getCurrencyRates() throws JsonProcessingException {
        log.info("{PrivatbankService}");
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

        String modifiedResponse = String.format("{\"currencyRates\":%s}", responseService);
        ResponseCurrencyRatesDTO responseCurrencyRates = objectMapper.readValue(modifiedResponse, ResponseCurrencyRatesDTO.class);

        StringBuilder response = new StringBuilder();

        for (CurrencyRate currencyRate : responseCurrencyRates.getCurrencyRates()) {
            if (currencyRate.getCcy().equals(CurrencyEnum.EUR.name())) {
                response.append(appendToString(CurrencyEnum.EUR, currencyRate.getBuy(), currencyRate.getSale()));
            } else if (currencyRate.getCcy().equals(CurrencyEnum.USD.name())) {
                response.append(appendToString(CurrencyEnum.USD, currencyRate.getBuy(), currencyRate.getSale()));
            }
        }

        return String.format("[%s]", response.substring(0, response.length() - 1));

    }

    private String appendToString(CurrencyEnum currency, String buy, String sale) {

        StringBuilder currencyRate = new StringBuilder();

        currencyRate.append(String.format("{\"providerType\":\"%s\",", ProviderEnum.PRIVATBANK));
        currencyRate.append(String.format("\"currencyType\":\"%s\",", currency.toString()));
        currencyRate.append(String.format("\"buy\":\"%s\",", buy));
        currencyRate.append(String.format("\"sale\":\"%s\"},", sale));

        return currencyRate.toString();

    }

}