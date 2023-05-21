package com.facens.apimarketplace.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends GenericException {

    public BadRequestException(String message, HttpStatus status) {
        super(message, status);
    }
}
