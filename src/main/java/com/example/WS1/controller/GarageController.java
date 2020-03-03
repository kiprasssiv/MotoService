package com.example.WS1.controller;

import com.example.WS1.controller.request.CreateMotorcycleRequest;
import com.example.WS1.controller.request.DeleteMotorcycleRequest;
import com.example.WS1.controller.request.UpdateMotorcycleRequest;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.service.GarageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GarageController {

    private GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }


    @GetMapping("/motorcycles")
    public List<Motorcycle> getMotorcycles() {
        return garageService.getMotorcycles();
    }

    @GetMapping("/motorcycles/{id}")
    public Motorcycle getMortorcycle(@PathVariable("id") String id) throws Exception {
        return garageService.getMotorcycle(UUID.fromString(id));
    }


    @PostMapping("/motorcycle")
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody CreateMotorcycleRequest request) {

        return garageService.createMotorcycle(request.getMake(),request.getModel(),request.getYear());

    }

    @PutMapping("/motorcycle/{id}")
    public Motorcycle updateMotorcycleInfo(
            @PathVariable("id") String id,
            @RequestBody UpdateMotorcycleRequest request
    ) throws Exception{
        try {
            return garageService.updateMotorcycle(UUID.fromString(id), request.getMake(), request.getModel(), request.getYear(), request.isNeedFixing());
        }catch(Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/deletedmotorcycle/{id}")
    public ResponseEntity<Motorcycle> deleteMotorcycleInfo(
            @PathVariable("id") String id
    ) throws Exception{
        garageService.deleteMotorcycle(UUID.fromString(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
