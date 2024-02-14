package com.httpclient.controller;

import com.dto.Request.UrlRequestDTO;
import com.dto.Response.ErrorClientResponseDTO;
import com.httpclient.service.HttpClientService;
import com.interfaces.httpclient.IHttpClientController;
import com.interfaces.httpclient.IHttpClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@Tag(name = "Http client API.",
     description = "API for getting data from remote services.")
@RequestMapping("/api")
public class HttpClientController implements IHttpClientController {

    private final IHttpClientService httpClientService;

    @Autowired
    public HttpClientController(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
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
    @GetMapping(value = "/get/currency-rates", produces = "application/json")
    public String getServiceResponse(@RequestBody UrlRequestDTO request) throws IOException, InterruptedException {
        log.info("{HttpClientController::getServiceResponse}");
        log.info("RequestBody: {}", request.toString());
        return httpClientService.getServiceResponse(request.getUrl());
    }

}