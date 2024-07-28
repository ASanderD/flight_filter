package com.gridnine.testing.exception;

public class FlightsNotFoundException extends RuntimeException {
    private final String message;

    public FlightsNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
