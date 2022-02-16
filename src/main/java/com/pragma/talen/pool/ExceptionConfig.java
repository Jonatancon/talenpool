package com.pragma.talen.pool;

import com.pragma.talen.pool.domain.exceptions.ConflictException;
import com.pragma.talen.pool.domain.exceptions.NotFountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<String> conflictException(ConflictException conflictException) {
        return new ResponseEntity<>(conflictException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<String> notFoundException(NotFountException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
