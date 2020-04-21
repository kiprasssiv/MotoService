package com.example.WS1.model;
import com.example.WS1.model.enums.DefectPriority;
import com.example.WS1.model.enums.DefectStatus;
import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;
import java.util.Date;


public class DefectEntity {

    @ApiModelProperty(required = false, hidden = true)
    private long id;
    private String name;
    private DefectPriority priority;
    private DefectStatus status;
    private String description;
    @ApiModelProperty(required = false, hidden = true)
    private Date dateCreated;
    @ApiModelProperty(required = false, hidden = true)
    private Date dateUpdated;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
