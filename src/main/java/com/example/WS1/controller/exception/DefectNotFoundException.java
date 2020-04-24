package com.example.WS1.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Defekt not found")
public class DefectNotFoundException extends RuntimeException {
    public DefectNotFoundException(){

    }

}
