package com.currencyratesconsumer.dto;

import com.currencyratesconsumer.model.CurrencyRatesEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyRatesDTO {

    public CurrencyRatesEntity[] currencyRates;

    public CurrencyRatesDTO() {
    }

}