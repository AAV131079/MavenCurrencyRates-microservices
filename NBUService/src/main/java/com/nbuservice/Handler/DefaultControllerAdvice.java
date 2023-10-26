package com.nbuservice.Handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbuservice.dto.response.ErrorClientResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleIOException(JsonProcessingException e) {
        responseEntity = new ResponseEntity<>(new ErrorClientResponseDTO("JsonProcessingException", e.getMessage()), HttpStatus.BAD_REQUEST);
        log.info(HttpStatus.BAD_REQUEST.toString(), "IOException", e);
        return responseEntity;
    }

}