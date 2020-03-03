package com.example.WS1.controller.exception;

import com.example.WS1.model.Motorcycle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Product not found")
public class MotorcycleNotFoundException extends RuntimeException {
    public MotorcycleNotFoundException(){

    }

}
