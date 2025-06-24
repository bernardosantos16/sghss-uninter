package com.uninter.api.sghss.infra.exceptions.handlers;

import com.uninter.api.sghss.infra.exceptions.errors.Erro400;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(Erro400::new)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
//        var error = ex.getMessage();
//        return ResponseEntity.badRequest().body(error);
//    }

}
