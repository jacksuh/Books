package com.genre.books.exception;
public class ValidationException extends RuntimeException {

    public ValidationException(String mensagem) {
        super(mensagem);
    }
}
