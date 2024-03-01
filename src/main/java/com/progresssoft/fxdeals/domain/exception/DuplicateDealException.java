package com.progresssoft.fxdeals.domain.exception;


public class DuplicateDealException extends RuntimeException {

    public DuplicateDealException(String message) {
        super(message);
    }

    public DuplicateDealException(String message, Throwable cause) {
        super(message, cause);
    }
}
