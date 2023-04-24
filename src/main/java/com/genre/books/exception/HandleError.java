package com.genre.books.exception;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleError {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleErrorLogin(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
