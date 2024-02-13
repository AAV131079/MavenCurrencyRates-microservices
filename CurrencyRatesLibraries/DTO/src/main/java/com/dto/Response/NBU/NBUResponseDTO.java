package com.dto.Response.NBU;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NBUResponseDTO {

    public CurrencyRateNBU[] currencyRateNBU;

}