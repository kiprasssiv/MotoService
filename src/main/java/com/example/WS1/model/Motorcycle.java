package com.example.WS1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "motorcycles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Motorcycle {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonProperty("id")
    private UUID id = UUID.randomUUID();
    @Column(name = "make")
    @JsonProperty("make")
    private String make;
    @Column(name = "model")
    @JsonProperty("model")
    private String model;
    @Column(name = "year")
    @JsonProperty("year")
    private int year;
    @Column(name = "needfixing")
    @JsonProperty("needFixing")
    private boolean needFixing = true;

    @OneToMany(mappedBy = "moto_id", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Defect> defects = new ArrayList<>();

    public Motorcycle(String make, String model, int year){
        this.make = make;
        this.model = model;
        this.year = year;
    }
    public Motorcycle(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isNeedFixing() {
        return needFixing;
    }

    public void setNeedFixing(boolean needFixing) {
        this.needFixing = needFixing;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }
    
}


