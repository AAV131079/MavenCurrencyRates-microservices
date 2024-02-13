package com.dto.Response.Monobank;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyRateMonobank {

    private Long currencyCodeA;
    private Long currencyCodeB;
    private Date date;
    private Float rateBuy;
    private Long rateCross;
    private Float rateSell;

}