package com.pragma.talen.pool.domain.exceptions;

public class NotFountException extends RuntimeException{
    private static final String DESCRIPTION = "Not Found Exception (404)";

    public NotFountException(String details) {
        super(DESCRIPTION + ". " + details);
    }
}
