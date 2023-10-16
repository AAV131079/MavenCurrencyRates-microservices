package com.currencyratesclient.Handler;

import com.currencyratesclient.dto.Response.ErrorClientResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.text.ParseException;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorClientResponseDTO> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorClientResponseDTO("Exception", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleIOException(IOException e) {
        return new ResponseEntity<>(new ErrorClientResponseDTO("IOException", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleParseException(ParseException e) {
        return new ResponseEntity<>(new ErrorClientResponseDTO("ParseException", e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<ErrorClientResponseDTO> handleInterruptedException(InterruptedException e) {
        return new ResponseEntity<>(new ErrorClientResponseDTO("InterruptedException", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}