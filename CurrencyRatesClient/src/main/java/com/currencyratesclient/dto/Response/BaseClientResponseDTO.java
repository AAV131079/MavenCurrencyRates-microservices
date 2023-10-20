package com.currencyratesclient.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseClientResponseDTO {

    @Schema(description = "Description of response", example = "The average exchange rate for period (Privatbank, Monobank, NBU)")
    private String description;
    @Schema(description = "Start date and time of period in the format: yyyy-MM-dd HH:mm:ss.", example = "2023-05-04 00:00:00")
    private String startDateTime;
    @Schema(description = "End date and time of period in the format: yyyy-MM-dd HH:mm:ss.", example = "2023-05-11 23:59:59")
    private String finishDateTime;
    @Schema(description = "Warning message", example = "Nothing was found in the specified period.")
    private String message;

    public BaseClientResponseDTO(String startDate, String finishDate, String description, String message) {
        this.description = description;
        this.startDateTime = startDate;
        this.finishDateTime = finishDate;
        this.message = message;
    }

    @Override
    public String toString() {

        StringBuilder outputString = new StringBuilder();

        outputString.append("BaseClientResponseDTO{");
        outputString.append("description='");
        outputString.append(description);
        outputString.append('\'');
        outputString.append(", startDateTime='");
        outputString.append(startDateTime);
        outputString.append('\'');
        outputString.append(", finishDateTime='");
        outputString.append(finishDateTime);
        outputString.append('\'');
        outputString.append(", message='");
        outputString.append(message);
        outputString.append('\'');
        outputString.append('}');

        return  outputString.toString();

    }

}