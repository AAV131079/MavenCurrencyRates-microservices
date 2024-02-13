package com.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FullClientResponseDTO extends BaseClientResponseDTO {

    private CurrencyRate[] currencyRates;

    public FullClientResponseDTO(String startDate, String finishDate, String description, String message) {
        super(startDate, finishDate, description, message);
    }

    @Override
    public String toString() {

        StringBuilder outputString = new StringBuilder();

        outputString.append("FullClientResponseDTO{");
        outputString.append("currencyRates=");
        outputString.append(Arrays.toString(currencyRates));
        outputString.append('}');

        return  outputString.toString();

    }

}