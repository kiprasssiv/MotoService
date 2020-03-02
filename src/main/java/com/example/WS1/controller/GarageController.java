package com.example.WS1.controller;

import com.example.WS1.controller.request.CreateMotorcycleRequest;
import com.example.WS1.controller.request.DeleteMotorcycleRequest;
import com.example.WS1.controller.request.UpdateMotorcycleRequest;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.service.GarageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "External api error")
})
public class GarageController {

    private GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @ApiOperation(value = "Get all motorcycles")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Motorcycles not found")
    })
    @GetMapping("/motorcycles")
    public ResponseEntity<List<Motorcycle>> getMotorcycles() {
        List<Motorcycle> motorcycles = garageService.getMotorcycles();
        return ResponseEntity.ok(motorcycles);
    }

    @ApiOperation(code = 200, value = "Get motorcycle by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Motorcycle not found")
    })
    @GetMapping("/motorcycles/{id}")
    public ResponseEntity<Motorcycle> getMortorcycle(@PathVariable("id") String id) throws Exception {
        Motorcycle motorcycle = garageService.getMotorcycle(UUID.fromString(id));
        return ResponseEntity.ok(motorcycle);
    }


    @PostMapping("/motorcycle")
    public Motorcycle createMotorcycle(@RequestBody CreateMotorcycleRequest request) throws Exception{
        try{
        return garageService.createMotorcycle(request.getMake(),request.getModel(),request.getYear());
        } catch(Exception ex){
            throw ex;
        }
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
    public Motorcycle deleteMotorcycleInfo(
            @PathVariable("id") String id
    ) throws Exception{
        try {
            return garageService.deleteMotorcycle(UUID.fromString(id));
        }catch (Exception ex){
            throw ex;
        }
    }

}
