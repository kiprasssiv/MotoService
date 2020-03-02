package com.example.WS1.controller;

import com.example.WS1.controller.request.CreateMotorcycleRequest;
import com.example.WS1.controller.request.DeleteMotorcycleRequest;
import com.example.WS1.controller.request.UpdateMotorcycleRequest;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.service.GarageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GarageController {

    private GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }
    //@RequestParam(value = "name", defaultValue = "defaultMoto") String name
    @GetMapping("/motorcycles")
    public List<Motorcycle> getMotorcycles() {
        return garageService.getMotorcycles();
    }

    @GetMapping("/motorcycles/{id}")
    public Motorcycle getMortorcycle(@PathVariable("id") String id) throws Exception{
        try {
            return garageService.getMotorcycle(UUID.fromString(id));
        } catch(Exception ex) {
            throw ex;
        }
    }

    @PostMapping("/motorcycle")
    public Motorcycle createMotorcycle(@RequestBody CreateMotorcycleRequest request) {
        return garageService.createMotorcycle(request.getMake(),request.getModel(),request.getYear());
    }

    @PutMapping("/motorcycle/{id}")
    public Motorcycle updateMotorcycleInfo(
            @PathVariable("id") String id,
            @RequestBody UpdateMotorcycleRequest request
    ) throws Exception{
        return garageService.updateMotorcycle(UUID.fromString(id), request.getMake(),request.getModel(),request.getYear(), request.isNeedFixing());
    }

    @DeleteMapping("/deletedmotorcycle/{id}")
    public Motorcycle deleteMotorcycleInfo(
            @PathVariable("id") String id,
            @RequestBody DeleteMotorcycleRequest request
    ) throws Exception{
        return garageService.deleteMotorcycle(UUID.fromString(id));
    }

}
