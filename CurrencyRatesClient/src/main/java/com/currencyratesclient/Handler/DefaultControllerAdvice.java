package com.currencyratesclient.Handler;

import com.currencyratesclient.dto.Response.ErrorClientResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class DefaultControllerAdvice {

    private ResponseEntity<ErrorClientResponseDTO> responseEntity;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorClientResponseDTO> handleException(Exception e) {
        responseEntity = new ResponseEntity<>(new ErrorClientResponseDTO("Exception", e.getMessage()), HttpStatus.BAD_REQUEST);
        log.info(HttpStatus.BAD_REQUEST.toString(), "Exception", e);
        return responseEntity;
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleIOException(IOException e) {
        responseEntity = new ResponseEntity<>(new ErrorClientResponseDTO("IOException", e.getMessage()), HttpStatus.BAD_REQUEST);
        log.info(HttpStatus.BAD_REQUEST.toString(), "IOException", e);
        return responseEntity;
    }

}