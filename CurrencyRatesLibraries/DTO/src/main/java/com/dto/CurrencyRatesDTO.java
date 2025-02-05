package com.dto;

import com.dao.CurrencyRatesEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyRatesDTO {

    public CurrencyRatesEntity[] currencyRates;

}