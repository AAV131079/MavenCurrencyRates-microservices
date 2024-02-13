package com.dto.Response.Privatbank;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrivatbankResponseDTO {

    public CurrencyRatePrivatbank[] currencyRatePrivatbank;

}