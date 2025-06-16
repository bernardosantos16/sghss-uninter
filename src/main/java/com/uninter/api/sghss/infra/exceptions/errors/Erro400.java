package com.uninter.api.sghss.infra.exceptions.errors;

import org.springframework.validation.FieldError;

public record Erro400(
        String field,
        String message
) {
    public Erro400(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
