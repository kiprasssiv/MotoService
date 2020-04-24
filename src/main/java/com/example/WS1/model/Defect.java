package com.example.WS1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "defects")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Defect {

    @Column(name = "moto_id")
    @JsonProperty("moto_id")
    public UUID moto_id;

    @Column(name = "service_id")
    @JsonProperty("service_id")
    public int service_id;

    public Defect(UUID moto_id, int service_id){
        this.moto_id = moto_id;
        this.service_id = service_id;
    }

    public Defect() {}

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
}
