package com.monobankservice.service;

import com.dto.Request.UrlRequestDTO;
import com.dto.Response.Monobank.MonobankResponseDTO;
import com.enumerators.CurrencyEnum;
import com.enumerators.ProviderEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpers.objectmapper.ObjectMapperHelper;
import com.helpers.resttemplate.RestTemplateHelper;
import com.helpers.string.StringHelper;
import com.interfaces.monobank.IMonobankService;
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
public class MonobankService implements IMonobankService {

    private final RestTemplateHelper restTemplateHelper;
    private final ObjectMapperHelper objectMapperHelper;
    private final StringHelper stringHelper;

    private final String SERVICE_URI = "https://api.monobank.ua/bank/currency";
    private final String URI = "http://currency-rates-gateway/http-client/api/get/currency-rates";

    @Autowired
    public MonobankService(RestTemplateHelper restTemplateHelper, ObjectMapperHelper objectMapperHelper,
                           StringHelper stringHelper) {
        this.restTemplateHelper = restTemplateHelper;
        this.objectMapperHelper = objectMapperHelper;
        this.stringHelper = stringHelper;
    }

    @Override
    public String getCurrencyRates() throws JsonProcessingException, InterruptedException {
        log.info("{MonobankService::getCurrencyRates}");
        String uri = getUri();
        UrlRequestDTO requestBody = getRequestBody();
        log.info("Request: {}", uri);
        log.info("Request body: {}", requestBody.toString());
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

        String modifiedResponse = String.format("{\"currencyRateMonobank\": %s}", responseService);
        MonobankResponseDTO responseCurrencyRates = (MonobankResponseDTO) objectMapperHelper.fillDTO(modifiedResponse,
                MonobankResponseDTO.class);

        StringBuilder response = new StringBuilder();

        Arrays.stream(responseCurrencyRates.getCurrencyRateMonobank())
                .filter(currencyRate -> (currencyRate.getCurrencyCodeA() == 840 ||
                                        currencyRate.getCurrencyCodeA() == 978) &&
                                        currencyRate.getCurrencyCodeB() == 980
                )
                .forEach(currencyRate -> {
                    if (currencyRate.getCurrencyCodeA() == 840 && currencyRate.getCurrencyCodeB() == 980) {
                        response.append(
                                stringHelper.getCurrencyRate(ProviderEnum.MONOBANK, CurrencyEnum.USD,
                                        currencyRate.getRateBuy(),currencyRate.getRateSell())
                        );
                    } else if (currencyRate.getCurrencyCodeA() == 978 && currencyRate.getCurrencyCodeB() == 980) {
                        response.append(
                                stringHelper.getCurrencyRate(ProviderEnum.MONOBANK, CurrencyEnum.EUR,
                                        currencyRate.getRateBuy(), currencyRate.getRateSell())
                        );
                    }
                });

        return String.format("[%s]", response.substring(0, response.length() - 1));

    }

}