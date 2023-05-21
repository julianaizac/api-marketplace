package com.facens.apimarketplace.application.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class DefaultError {

    private Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private String message;
    private String cause;

}
