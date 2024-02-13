package com.exceptionhendler;

import com.dto.Response.ErrorClientResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class CurrencyRateExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorClientResponseDTO> handleException(@NotNull Exception e) {
        return getResponseEntity(e.getMessage(), e.getClass().getName());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleIOException(@NotNull IOException e) {
        return getResponseEntity(e.getMessage(), e.getClass().getName());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleJsonProcessingException(@NotNull JsonProcessingException e) {
        return getResponseEntity(e.getMessage(), e.getClass().getName());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleRuntimeException(@NotNull RuntimeException e) {
        return getResponseEntity(e.getMessage(), e.getClass().getName());
    }

    private @NotNull ResponseEntity<ErrorClientResponseDTO> getResponseEntity(String message, String exceptionName) {
        ResponseEntity<ErrorClientResponseDTO> responseEntity = new ResponseEntity<>(
                new ErrorClientResponseDTO(exceptionName, message), HttpStatus.BAD_REQUEST);
        log.info(HttpStatus.BAD_REQUEST.toString(), exceptionName, message);
        return responseEntity;
    }

}