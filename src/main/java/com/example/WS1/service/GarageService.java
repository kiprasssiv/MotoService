package com.example.WS1.service;

import com.example.WS1.controller.exception.MotorcycleNotFoundException;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.repository.MotorcycleRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GarageService {

    MotorcycleRepository motorcycleRepository;

    public GarageService(MotorcycleRepository motorcycleRepository){
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<Motorcycle> getMotorcycles() {
        List<Motorcycle> motorcycles = motorcycleRepository.findAll();
        return motorcycles;
    }

    public Motorcycle getMotorcycle(UUID id) throws Exception {
        try {
            return motorcycleRepository.getMotorcycleById(id)
                    .orElseThrow(() -> new Exception("Failed to find motorcycle by id"));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public ResponseEntity<Motorcycle> createMotorcycle(String make, String model, int year){
        Motorcycle motorcycle = new Motorcycle(make, model,year);
        Motorcycle moto = motorcycleRepository.save(motorcycle);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location","/motorcycles/" + moto.getId());
        try{
            return ResponseEntity.created(new URI("/motorcycles/"+moto.getId())).header(String.valueOf(responseHeaders)).body(motorcycle);
        }catch(Exception e){
            throw new RuntimeException("Failed creating");
        }
    }

    public Motorcycle updateMotorcycle(UUID id, String make, String model, int year, boolean needFixing)throws Exception{
        if(!motorcycleRepository.existsById(id)){
            throw new MotorcycleNotFoundException();
        }
        Motorcycle motorcycle = getMotorcycle(id);
        motorcycle.setId(id);
        motorcycle.setMake(make);
        motorcycle.setModel(model);
        motorcycle.setYear(year);
        motorcycle.setNeedFixing(needFixing);
        return motorcycleRepository.save(motorcycle);

    }

    public ResponseEntity<Motorcycle> deleteMotorcycle(UUID id) throws Exception {
        if(!motorcycleRepository.existsById(id)){
            throw new MotorcycleNotFoundException();
        }
        Motorcycle motorcycle = getMotorcycle(id);
        motorcycleRepository.delete(motorcycle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
