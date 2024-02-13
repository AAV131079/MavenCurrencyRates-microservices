package com.privatbankservice.service;

import com.dto.Request.UrlRequestDTO;
import com.dto.Response.Privatbank.CurrencyRatePrivatbank;
import com.dto.Response.Privatbank.PrivatbankResponseDTO;
import com.enumerators.CurrencyEnum;
import com.enumerators.ProviderEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpers.objectmapper.ObjectMapperHelper;
import com.helpers.resttemplate.RestTemplateHelper;
import com.helpers.string.StringHelper;
import com.interfaces.privatbank.IPrivatbankService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ComponentScan({"com.helpers"})
public class PrivatbankService implements IPrivatbankService {

    private final RestTemplateHelper restTemplateHelper;
    private final ObjectMapperHelper objectMapperHelper;
    private final StringHelper stringHelper;

    private final String SERVICE_URI = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
    private final String URI = "http://currency-rates-gateway/http-client/api/get/currency-rates";

    @Autowired
    public PrivatbankService(RestTemplateHelper restTemplateHelper, ObjectMapperHelper objectMapperHelper,
                             StringHelper stringHelper) {
        this.restTemplateHelper = restTemplateHelper;
        this.objectMapperHelper = objectMapperHelper;
        this.stringHelper = stringHelper;
    }

    @Override
    public String getCurrencyRates() throws JsonProcessingException {
        log.info("{PrivatbankService::getCurrencyRates}");
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

        String modifiedResponse = String.format("{\"currencyRatePrivatbank\":%s}", responseService);
        PrivatbankResponseDTO responseCurrencyRates = (PrivatbankResponseDTO) objectMapperHelper.fillDTO(modifiedResponse,
                PrivatbankResponseDTO.class);

        StringBuilder response = new StringBuilder();

        for (CurrencyRatePrivatbank currencyRatePrivatbank : responseCurrencyRates.getCurrencyRatePrivatbank()) {
            if (currencyRatePrivatbank.getCcy().equals(CurrencyEnum.EUR.name())) {
                response.append(
                        stringHelper.getCurrencyRate(ProviderEnum.PRIVATBANK, CurrencyEnum.EUR,
                                currencyRatePrivatbank.getBuy(), currencyRatePrivatbank.getSale())
                );
            } else if (currencyRatePrivatbank.getCcy().equals(CurrencyEnum.USD.name())) {
                response.append(
                        stringHelper.getCurrencyRate(ProviderEnum.PRIVATBANK, CurrencyEnum.USD,
                                currencyRatePrivatbank.getBuy(), currencyRatePrivatbank.getSale())
                );
            }
        }

        return String.format("[%s]", response.substring(0, response.length() - 1));

    }

}