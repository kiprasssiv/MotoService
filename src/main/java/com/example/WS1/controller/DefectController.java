package com.example.WS1.controller;

import com.example.WS1.controller.request.CreateMotoDefektRequest;
import com.example.WS1.controller.request.UpdateMotoDefektRequest;
import com.example.WS1.model.DefectEntity;
import com.example.WS1.model.Defekt;
import com.example.WS1.service.DefectService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class DefectController {
    private DefectService defectService;

    public DefectController(DefectService defectService) {
        this.defectService = defectService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle of deffects list"),
            @ApiResponse(code = 404, message = "Database empty")
    })
    @GetMapping("/motorcycles/deffects")
    public List<Defekt> getDefekts() {
        return defectService.getDefekts();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deffect list"),
            @ApiResponse(code = 404, message = "Deffect list is empty")
    })
    @GetMapping("/motorcycles/deffects/posibilities")
    public List<DefectEntity> getDefektList() {
        return defectService.getDefektList();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle deffect found"),
            @ApiResponse(code = 404, message = "Motorcycle deffect not found")
    })
    @GetMapping("/motorcycles/deffects/{id}")
    public Defekt getDefekt(@PathVariable("id") String id) throws Exception {
        return defectService.getDefekt(UUID.fromString(id));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle deffect created"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle deffect not created")
    })
    @PostMapping("/motorcycles/deffects")
    public ResponseEntity<Defekt> createDefekt(@RequestBody CreateMotoDefektRequest request) {
        return defectService.createDefekt(request.getMoto_id(),request.getService_id());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated motorcycle deffect"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle deffect not found")
    })

    @PutMapping("/motorcycle/deffects/{id}")
    public Defekt updateDeffectInfo(
            @PathVariable("id") String id,
            @RequestBody UpdateMotoDefektRequest request
    ) throws Exception{
        try {
            return defectService.updateDefekt(UUID.fromString(id), request.getMoto_id(), request.getService_id());
        }catch(Exception ex){
            throw ex;
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "Motorcycle deffect deleted"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @DeleteMapping("/motorcycle/deffects/{id}")
    public ResponseEntity<Defekt> deleteMotoDefektInfo(
            @PathVariable("id") String id
    ) throws Exception{
        defectService.deleteDefekt(UUID.fromString(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}