package com.example.WS1.controller;

import com.example.WS1.controller.request.CreateMotorcycleRequest;
import com.example.WS1.controller.request.DeleteMotorcycleRequest;
import com.example.WS1.controller.request.UpdateMotorcycleRequest;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.service.GarageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(code = 200, value = "Get all motorcycles")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Database empty")
    })
    @GetMapping("/motorcycles")
    public List<Motorcycle> getMotorcycles() {
        return garageService.getMotorcycles();
    }

    @ApiOperation(code = 200, value = "Get product's defect by it's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Motorcycle not found")
    })
    @GetMapping("/motorcycles/{id}")
    public Motorcycle getMortorcycle(@PathVariable("id") String id) throws Exception {
        return garageService.getMotorcycle(UUID.fromString(id));
    }


    @ApiOperation(code = 201, value = "Add a motorcycle")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @PostMapping("/motorcycle")
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody CreateMotorcycleRequest request) {
        return garageService.createMotorcycle(request.getMake(),request.getModel(),request.getYear());

    }

    @ApiOperation(code = 200, value = "Update a motorcycle")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Not found")
    })
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

    @ApiOperation(code = 204, value = "Delete motorcycle by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found")
    })
    @DeleteMapping("/deletedmotorcycle/{id}")
    public ResponseEntity<Motorcycle> deleteMotorcycleInfo(
            @PathVariable("id") String id
    ) throws Exception{
        garageService.deleteMotorcycle(UUID.fromString(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
