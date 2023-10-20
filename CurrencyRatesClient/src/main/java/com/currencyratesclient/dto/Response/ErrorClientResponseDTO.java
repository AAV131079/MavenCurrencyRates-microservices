package com.currencyratesclient.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorClientResponseDTO {

    @Schema(description = "Exception type", example = "IOException")
    private String errorType;
    @Schema(description = "Error description", example = "Full text of error")
    private String errorDescription;

    public ErrorClientResponseDTO(String errorType, String errorDescription) {
        this.errorType = errorType;
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {

        StringBuilder outputString = new StringBuilder();

        outputString.append("ErrorClientResponseDTO{");
        outputString.append("errorType='");
        outputString.append(errorType);
        outputString.append('\'');
        outputString.append(", errorDescription='");
        outputString.append(errorDescription);
        outputString.append('\'');
        outputString.append('}');

        return  outputString.toString();

    }

}