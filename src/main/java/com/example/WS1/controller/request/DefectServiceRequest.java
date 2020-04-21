package com.example.WS1.controller.request;

import com.example.WS1.model.enums.DefectPriority;
import com.example.WS1.model.enums.DefectStatus;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;

@Validated
public class DefectServiceRequest {
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
}
