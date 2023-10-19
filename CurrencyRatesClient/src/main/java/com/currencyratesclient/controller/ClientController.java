package com.currencyratesclient.controller;

import com.currencyratesclient.dto.Request.RequestDTO;
import com.currencyratesclient.dto.Response.BaseClientResponseDTO;
import com.currencyratesclient.dto.Response.ErrorClientResponseDTO;
import com.currencyratesclient.dto.Response.FullClientResponseDTO;
import com.currencyratesclient.dto.Response.ShortClientResponseDTO;
import com.currencyratesclient.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@Tag(name = "Exchange rates API.",
     description = "API for obtaining exchange rates using the data of three financial institutions (Monobank, Privatbank, National Bank of Ukraine).")
@RequestMapping("/rates")
public class ClientController {

     private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Get average exchange rates on the current day.",
               description = "Request for a list of average exchange rates for all sources on the current day.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                         description = "The request was completed successfully.",
                         content = @Content(schema = @Schema(implementation = FullClientResponseDTO.class))),
            @ApiResponse(responseCode = "206",
                    description = "Not found data in storage by your request.",
                    content = @Content(schema = @Schema(implementation = ShortClientResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                         description = "Error while executing request.",
                         content = @Content(schema = @Schema(implementation = ErrorClientResponseDTO.class)))
    })
    @GetMapping("/current")
    private ResponseEntity<BaseClientResponseDTO> getForCurrentDay() throws ParseException {
        Map<BaseClientResponseDTO, HttpStatus> result = clientService.getForCurrentDay();
        return new ResponseEntity<>((BaseClientResponseDTO) result.keySet().stream().toArray()[0],
                                    (HttpStatus) result.values().toArray()[0]);
    }

    @Operation(summary = "Get average exchange rates for the period.",
               description = "Request for a list of average exchange rates for all sources for the period. The start and end dates are specified in the request body.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Start and end date in the format: YYYY-MM-DD.",
            required    = true,
            content     = @Content(schema = @Schema(implementation = RequestDTO.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "The request was completed successfully.",
                    content = @Content(schema = @Schema(implementation = FullClientResponseDTO.class))),
            @ApiResponse(responseCode = "206",
                    description = "Not found data in storage by your request.",
                    content = @Content(schema = @Schema(implementation = ShortClientResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                         description = "Error while executing request.",
                         content = @Content(schema = @Schema(implementation = ErrorClientResponseDTO.class)))
    })
    @GetMapping("/period")
    private ResponseEntity<BaseClientResponseDTO> getForPeriodJson(@org.springframework.web.bind.annotation.RequestBody RequestDTO requestDTO) throws ParseException {
        Map<BaseClientResponseDTO, HttpStatus> result = clientService.getForPeriod(requestDTO.getStartDate(), requestDTO.getFinishDate());
        return new ResponseEntity<>((BaseClientResponseDTO) result.keySet().stream().toArray()[0],
                                    (HttpStatus) result.values().toArray()[0]);
    }

    @Operation(summary = "Get average exchange rates for the period.",
            description = "Request for a list of average exchange rates for all sources for the period. " +
                          "The start and end date are specified in the request path" +
                          " in the format: /rates/period/start=YYYY-MM-DD/finish=YYYY-MM-DD.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "startDate",
                            required = true,
                            description = "Period start date in the format: YYYY-MM-DD.",
                            schema = @Schema(allOf = { String.class }),
                            style = ParameterStyle.SIMPLE
                    ),
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "finishDate",
                            required = true,
                            description = "Period end date in the format: YYYY-MM-DD.",
                            schema = @Schema(allOf = { String.class }),
                            style = ParameterStyle.SIMPLE
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "The request was completed successfully.",
                    content = @Content(schema = @Schema(implementation = FullClientResponseDTO.class))),
            @ApiResponse(responseCode = "206",
                    description = "Not found data in storage by your request.",
                    content = @Content(schema = @Schema(implementation = ShortClientResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                         description = "Error while executing request.",
                         content = @Content(schema = @Schema(implementation = ErrorClientResponseDTO.class)))
    })
    @GetMapping("/period/start={startDate}/finish={finishDate}")
    private ResponseEntity<BaseClientResponseDTO> getForPeriodUrl(@PathVariable String startDate, @PathVariable String finishDate) throws ParseException {
        Map<BaseClientResponseDTO, HttpStatus> result = clientService.getForPeriod(startDate, finishDate);
        return new ResponseEntity<>((BaseClientResponseDTO) result.keySet().stream().toArray()[0],
                                    (HttpStatus) result.values().toArray()[0]);
    }

}