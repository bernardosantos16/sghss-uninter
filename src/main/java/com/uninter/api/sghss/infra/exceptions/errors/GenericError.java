package com.uninter.api.sghss.infra.exceptions.errors;

import org.springframework.validation.FieldError;

public record GenericError(
        String field,
        String message
) {
    public GenericError(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
