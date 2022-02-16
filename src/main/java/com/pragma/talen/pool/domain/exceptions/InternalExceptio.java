package com.pragma.talen.pool.domain.exceptions;

public class InternalExceptio extends RuntimeException{
    private static final String DESCRIPTION = "Internal Exception (500)";

    public InternalExceptio(String details) {
        super(DESCRIPTION + ". " + details);
    }
}
