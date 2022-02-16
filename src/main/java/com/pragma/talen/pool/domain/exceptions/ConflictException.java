package com.pragma.talen.pool.domain.exceptions;

public class ConflictException extends RuntimeException {
    private static final String DESCRIPTION = "Conflict Exception (409)";

    public ConflictException(String details) {
        super(DESCRIPTION + ". " + details);
    }

}
