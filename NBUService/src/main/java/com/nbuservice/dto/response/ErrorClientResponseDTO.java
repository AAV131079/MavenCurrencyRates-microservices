package com.nbuservice.dto.response;

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

}