package com.uninter.api.sghss.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessebleEntityException extends RuntimeException {
    public UnprocessebleEntityException(String message) {
        super(message);
    }
}
