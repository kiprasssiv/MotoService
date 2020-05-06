package com.example.WS1.controller.request;

import com.example.WS1.model.enums.DefectPriority;
import com.example.WS1.model.enums.DefectStatus;

import java.util.UUID;

public class MotorcycleRequest {

    private String make;
    private String model;
    private int year;
    private UUID moto_id;
    private int service_id;

    private String name;
    private String description;
    private DefectPriority priority;
    private DefectStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DefectPriority getPriority() {
        return priority;
    }

    public void setPriority(DefectPriority priority) {
        this.priority = priority;
    }

    public DefectStatus getStatus() {
        return status;
    }

    public void setStatus(DefectStatus status) {
        this.status = status;
    }
    public UUID getMoto_id() {
        return moto_id;
    }

    public void setMoto_id(UUID moto_id) {
        this.moto_id = moto_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
