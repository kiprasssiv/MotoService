package com.example.WS1.controller;

import com.example.WS1.controller.request.CreateMotoDefektRequest;
import com.example.WS1.controller.request.CreateMotorcycleRequest;
import com.example.WS1.controller.request.DefectServiceRequest;
import com.example.WS1.controller.request.UpdateMotoDefektRequest;
import com.example.WS1.model.DefectEntity;
import com.example.WS1.model.Defekt;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.service.DefectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
            @ApiResponse(code = 200, message = "Motorcycle of defects list"),
            @ApiResponse(code = 404, message = "Database empty")
    })
    @GetMapping("/motorcycles/defects")
    public List<Defekt> getDefects() {
        return defectService.getDefekts();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Defect list"),
            @ApiResponse(code = 404, message = "Defect list is empty")
    })
    @GetMapping("/motorcycles/defects/tasks")
    public List<DefectEntity> getDefectList() {
        return defectService.getDefektList();
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle defect found"),
            @ApiResponse(code = 404, message = "Motorcycle defect not found")
    })
    @GetMapping("/motorcycles/defects/{id}")
    public Defekt getDefect(@PathVariable("id") String id) throws Exception {
        return defectService.getDefekt(UUID.fromString(id));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle defect created"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle defect not created")
    })
    @PostMapping("/motorcycles/defects")
    public ResponseEntity<Defekt> createDefect(@RequestBody CreateMotoDefektRequest request) {
        return defectService.createDefekt(request.getMoto_id(),request.getService_id());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated motorcycle defect"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle defect not found")
    })

    @PutMapping("/motorcycle/defects/{id}")
    public Defekt updateDefectInfo(
            @PathVariable("id") String id,
            @RequestBody UpdateMotoDefektRequest request
    ) throws Exception{
        try {
            return defectService.updateDefekt(UUID.fromString(id), request.getMoto_id(), request.getService_id());
        }catch(Exception ex){
            throw ex;
        }
    }

    //Commands to work with external web service
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "Motorcycle defect deleted"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @DeleteMapping("/motorcycle/defects/{id}")
    public ResponseEntity<Defekt> deleteMotoDefectInfo(
            @PathVariable("id") String id
    ) throws Exception{
        defectService.deleteDefekt(UUID.fromString(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle defect added"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle defect not created")
    })
    @PostMapping("/motorcycles/defects/tasks")
    public ResponseEntity<DefectEntity> createNewDefect(@RequestBody DefectEntity request) {
        return defectService.addDefectToTheList(request.getName(),request.getDescription(),request.getPriority(),request.getStatus());

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated motorcycle defect"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle defect not found")
    })

    @PutMapping("/motorcycle/defects/tasks/{id}")
    public ResponseEntity<DefectEntity> updateDefectInfoList(
            @PathVariable("id") String id,
            @RequestBody DefectServiceRequest request
    ) throws Exception{
        try {
            return defectService.updateDefectList(Long.parseLong(id),request.getName(),request.getDescription(),request.getStatus(),request.getPriority());
        }catch(Exception ex){
            throw ex;
        }
    }



}
