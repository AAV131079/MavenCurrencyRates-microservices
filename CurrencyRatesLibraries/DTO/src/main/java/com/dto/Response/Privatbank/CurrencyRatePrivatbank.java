package com.dto.Response.Privatbank;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyRatePrivatbank {

    private String ccy;
    private String base_ccy;
    private Float buy;
    private Float sale;

}