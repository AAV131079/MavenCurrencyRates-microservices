package com.currencyratesclient.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortClientResponseDTO extends BaseClientResponseDTO {

    public ShortClientResponseDTO(String startDate, String finishDate, String description) {
        super(startDate, finishDate, description);
    }

}