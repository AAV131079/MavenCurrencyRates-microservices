package com.privatbankservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.privatbankservice.dto.CurrencyRate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCurrencyRatesDTO {

    public CurrencyRate[] currencyRates;

    public ResponseCurrencyRatesDTO() {
    }

}