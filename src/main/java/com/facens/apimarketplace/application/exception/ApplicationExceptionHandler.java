package com.facens.apimarketplace.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException exception){
        DefaultError error = new DefaultError(exception.getStatus().value(), exception.getMessage(), null);
        return ResponseEntity.status(error.getStatus()).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception){
        DefaultError error = new DefaultError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), null);
        return ResponseEntity.status(error.getStatus()).body(error);
    }


}
