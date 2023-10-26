package com.nbuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyRate {

    private Long r030;
    private String txt;
    private Float rate;
    private String cc;
    private String exchangedate;

    public CurrencyRate() {
    }

}