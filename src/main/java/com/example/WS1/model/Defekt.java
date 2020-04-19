package com.example.WS1.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "defects")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Defekt {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonProperty("id")
    private UUID id = UUID.randomUUID();
    @Column(name = "moto_id")
    @JsonProperty("moto_id")
    private UUID moto_id;
    @Column(name = "service_id")
    @JsonProperty("service_id")
    private int service_id;

    public Defekt(UUID moto_id, int service_id){
        this.moto_id = moto_id;
        this.service_id = service_id;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
