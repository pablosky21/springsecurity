package com.app.useraplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.useraplication.dto.ErrorDto;



@ControllerAdvice
public class CustomRestExceptionHandler   {

  

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ErrorDto> missingRequestHeaderException(MissingRequestHeaderException ex) {
        ErrorDto error =  ErrorDto.builder().code("400").message(ex.getLocalizedMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    

}
