package com.example.WS1.controller.exception;

public class ExternalApiException extends RuntimeException {

    private String message;

    public static ExternalApiException create() {
        return new ExternalApiException("Unable to finish the command. External server problem.");
    }

    private ExternalApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
