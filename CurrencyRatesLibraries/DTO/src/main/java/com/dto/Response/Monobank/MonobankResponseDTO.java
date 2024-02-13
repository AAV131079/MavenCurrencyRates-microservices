package com.dto.Response.Monobank;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonobankResponseDTO {

    public CurrencyRateMonobank[] currencyRateMonobank;

}