package com.currencyratesclient.controller;

import com.currencyratesclient.dto.Request.RequestDTO;
import com.currencyratesclient.dto.Response.BaseClientResponseDTO;
import com.currencyratesclient.dto.Response.ErrorClientResponseDTO;
import com.currencyratesclient.dto.Response.FullClientResponseDTO;
import com.currencyratesclient.dto.Response.ShortClientResponseDTO;
import com.currencyratesclient.service.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Tag(name = "Exchange rates API.",
     description = "API for obtaining exchange rates using the data of three financial institutions (Monobank, Privatbank, National Bank of Ukraine).")
@RequestMapping("/rates")
public class ClientController implements IClientController {

     private final IClientService clientService;

    public ClientController(IClientService clientService) {
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
    @Override
    @GetMapping("/current")
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForCurrentDay() {
        log.info("{ClientController}");
        log.info("Uri: /rates/current, type: GET");
        Map<BaseClientResponseDTO, HttpStatus> result = clientService.getForCurrentDay();
        ResponseEntity<BaseClientResponseDTO> responseEntity = new ResponseEntity<>(
                (BaseClientResponseDTO) result.keySet().toArray()[0],
                (HttpStatus) result.values().toArray()[0]);
        log.info(responseEntity.toString());
        return responseEntity;
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
    @Override
    @GetMapping("/period")
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForPeriodJson(@org.springframework.web.bind.annotation.RequestBody RequestDTO requestDTO) {
        log.info("{ClientController}");
        log.info("Uri: /rates/period, type: GET, RequestBody: RequestDTO.class");
        Map<BaseClientResponseDTO, HttpStatus> result = clientService.getForPeriod(requestDTO.getStartDate(), requestDTO.getFinishDate());
        ResponseEntity<BaseClientResponseDTO> responseEntity = new ResponseEntity<>(
                (BaseClientResponseDTO) result.keySet().toArray()[0],
                (HttpStatus) result.values().toArray()[0]);
        log.info(responseEntity.toString());
        return responseEntity;
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
    @Override
    @GetMapping("/period/start={startDate}/finish={finishDate}")
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForPeriodUrl(@PathVariable String startDate, @PathVariable String finishDate) {
        log.info("{ClientController}");
        log.info("Uri: /rates/period/start={startDate}/finish={finishDate}, type: GET, PathVariable: String startDate, PathVariable: String finishDate");
        Map<BaseClientResponseDTO, HttpStatus> result = clientService.getForPeriod(startDate, finishDate);
        ResponseEntity<BaseClientResponseDTO> responseEntity = new ResponseEntity<>(
                (BaseClientResponseDTO) result.keySet().toArray()[0],
                (HttpStatus) result.values().toArray()[0]);
        log.info(responseEntity.toString());
        return responseEntity;
    }

}