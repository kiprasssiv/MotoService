package com.example.WS1.controller.exception;

import com.example.WS1.model.Motorcycle;

import java.util.UUID;

public class MotorcycleNotFoundException extends RuntimeException {
    private String message;

    public static MotorcycleNotFoundException create() {
        return new MotorcycleNotFoundException("No motorcycles were found in the database.");
    }

    public static MotorcycleNotFoundException createWith(UUID id) {
        return new MotorcycleNotFoundException("No motorcycle was found in the database with id [" + id + "].");

    }

    private MotorcycleNotFoundException(String msg) {
        this.message = msg;
    }

    @Override

    public String getMessage() {

        return this.message;

    }
}
