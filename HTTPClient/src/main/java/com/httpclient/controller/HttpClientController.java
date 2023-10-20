package com.httpclient.controller;

import com.httpclient.dto.request.RequestDTO;
import com.httpclient.dto.response.ErrorClientResponseDTO;
import com.httpclient.service.HttpClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@Tag(name = "Http client API.",
     description = "API for getting data from remote services.")
@RequestMapping("/api")
public class HttpClientController implements  IHttpClientController {

    private final HttpClientServiceImpl httpClientServiceImpl;

    public HttpClientController(HttpClientServiceImpl httpClientServiceImpl) {
        this.httpClientServiceImpl = httpClientServiceImpl;
    }

    @Operation(summary = "Get a data from remote services.",
               description = "Requesting a response from a remote service using the received link.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "The request was completed successfully.",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400",
                    description = "Error while executing request.",
                    content = @Content(schema = @Schema(implementation = ErrorClientResponseDTO.class)))
    })
    @Override
    @GetMapping(value = "/get/currencyrates", produces = "application/json")
    public @ResponseBody String getServiceResponse(@RequestBody RequestDTO request) throws IOException {
        log.info("HttpClientService::getServiceResponse", request.toString());
        String response =  httpClientServiceImpl.getServiceResponse(request.getUrl());
        log.info(response);
        return response;
    }

}