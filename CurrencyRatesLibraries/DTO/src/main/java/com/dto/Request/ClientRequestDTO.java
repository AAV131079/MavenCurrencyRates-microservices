package com.dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientRequestDTO {

    @Schema(description = "Start date in the format: YYYY-MM-DD.", example = "2023-05-01")
    private String startDate;
    @Schema(description = "End date in the format: YYYY-MM-DD.", example = "2023-05-05")
    private String finishDate;

}