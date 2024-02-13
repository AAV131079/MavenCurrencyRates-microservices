package com.dto.Response.NBU;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyRateNBU {

    private Long r030;
    private String txt;
    private Float rate;
    private String cc;
    private String exchangedate;

}