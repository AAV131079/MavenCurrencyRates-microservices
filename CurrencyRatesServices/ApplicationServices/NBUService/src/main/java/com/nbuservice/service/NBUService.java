package com.nbuservice.service;

import com.dto.Request.UrlRequestDTO;
import com.dto.Response.NBU.NBUResponseDTO;
import com.enumerators.CurrencyEnum;
import com.enumerators.ProviderEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpers.objectmapper.ObjectMapperHelper;
import com.helpers.resttemplate.RestTemplateHelper;
import com.helpers.string.StringHelper;
import com.interfaces.nbu.INBUService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@ComponentScan({"com.helpers"})
public class NBUService implements INBUService {

    private final RestTemplateHelper restTemplateHelper;
    private final ObjectMapperHelper objectMapperHelper;
    private final StringHelper stringHelper;

    private final String SERVICE_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final String URI = "http://currency-rates-gateway/http-client/api/get/currency-rates";

    @Autowired
    public NBUService(RestTemplateHelper restTemplateHelper, ObjectMapperHelper objectMapperHelper,
                      StringHelper stringHelper) {
        this.restTemplateHelper = restTemplateHelper;
        this.objectMapperHelper = objectMapperHelper;
        this.stringHelper = stringHelper;
    }

    @Override
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException {
        log.info("{NBUService::getCurrencyRates}");
        String uri = getUri();
        UrlRequestDTO requestBody = getRequestBody();
        log.info("Request: {}", uri);
        log.info("Request body: {}", requestBody);
        String responseService = restTemplateHelper.getResponse(uri, requestBody);
        assert responseService != null;
        String response = getResponse(responseService);
        log.info("Response: {}", response);
        log.info("Response size: {}", response.length());
        return response;
    }

    private String getUri() {
        return URI;
    }

    @Contract(value = " -> new", pure = true)
    private @NotNull UrlRequestDTO getRequestBody() {
        return new UrlRequestDTO(SERVICE_URI);
    }

    private String getResponse(String responseService) throws JsonProcessingException {

        String modifiedResponse = String.format("{\"currencyRateNBU\":%s}", responseService);
        NBUResponseDTO responseCurrencyRates = (NBUResponseDTO) objectMapperHelper.fillDTO(modifiedResponse,
                NBUResponseDTO.class);

        StringBuilder response = new StringBuilder();

        Arrays.stream(responseCurrencyRates.currencyRateNBU)
                .filter(currencyRate -> currencyRate.getCc().equals(CurrencyEnum.EUR.name()) ||
                        currencyRate.getCc().equals(CurrencyEnum.USD.name())
                )
                .forEach(currencyRate -> {
                    if (currencyRate.getCc().equals(CurrencyEnum.EUR.name())) {
                        response.append(
                                stringHelper.getCurrencyRate(ProviderEnum.NBU, CurrencyEnum.EUR,
                                        currencyRate.getRate(), currencyRate.getRate())
                        );
                    } else if (currencyRate.getCc().equals(CurrencyEnum.USD.name())) {
                        response.append(
                                stringHelper.getCurrencyRate(ProviderEnum.NBU, CurrencyEnum.USD,
                                        currencyRate.getRate(), currencyRate.getRate())
                        );
                    }
                });

        return String.format("[%s]", response.substring(0, response.length() - 1));

    }

}