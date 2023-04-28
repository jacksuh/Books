package com.genre.books.exception;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class HandleError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity Erro404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleError(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
