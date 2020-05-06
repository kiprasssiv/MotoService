package com.example.WS1.service;

import com.example.WS1.controller.exception.DefectNotFoundException;
import com.example.WS1.controller.exception.MotorcycleNotFoundException;
import com.example.WS1.controller.request.DefectServiceRequest;
import com.example.WS1.model.DefectEntity;
import com.example.WS1.model.Defect;
import com.example.WS1.model.DefectCaller;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.model.enums.DefectPriority;
import com.example.WS1.model.enums.DefectStatus;
import com.example.WS1.repository.DefectRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class DefectService {
    DefectRepository defectRepository;
    DefectCaller defectCaller;



    public DefectService(DefectRepository defectRepository, DefectCaller defectCaller){
        this.defectRepository = defectRepository;
        this.defectCaller = defectCaller;
    }

    //Get defect list
    public List<DefectEntity> getDefectList() {
        List<DefectEntity> defectList;
        defectList = defectCaller.getDefectList();
        return defectList;
    }

    //Get full list
    public List<Defect> getDefects() {
        List<Defect> defects = defectRepository.findAll();
        return defects;
    }

    public Defect getDefect(UUID id) throws Exception {
        try {
            return defectRepository.getDefectById(id)
                    .orElseThrow(() -> new Exception("Failed to find defect by id"));
        } catch (Exception ex) {
            throw ex;
        }
    }

    //Add a motorcycle deffect
    public ResponseEntity<Defect> createDefect(UUID moto_id, int def_id){
        boolean exists = false;
        List<DefectEntity> defectList;
        defectList = defectCaller.getDefectList();
        for(int i=0;i<defectList.size();i++){
            if((int)(defectList.get(i).getId()) == def_id){
                exists = true;
                break;
            }
        }
        if(exists){
            Defect defect = new Defect(moto_id,def_id);
            Defect def = defectRepository.save(defect);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location","/motorcycles/defects/" + def.getId());
            try{
                return ResponseEntity.created(new URI("/motorcycles/defects/"+def.getId())).header(String.valueOf(responseHeaders)).body(defect);
            }catch(Exception e){
                throw new RuntimeException("Failed creating");
            }
        }
        else{
            throw new DefectNotFoundException();
        }
    }

    //Update defect
    public Defect updateDefect(UUID id, UUID moto_id, int def_id)throws Exception{

        Defect defect = getDefect(id);
        defect.setMoto_id(moto_id);
        defect.setService_id(def_id);
        return defectRepository.save(defect);
    }

    //Delete a finished to fix deffect
    public ResponseEntity<Defect> deleteDefect(UUID id) throws Exception {
        if(defectRepository.existsById(id)){
            throw new DefectNotFoundException();
        }
        Defect def = getDefect(id);
        defectRepository.delete(def);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Add a new defect to the list
    public ResponseEntity<DefectEntity> addDefectToTheList(String name, String description, DefectPriority priority, DefectStatus status){
        DefectServiceRequest defectEntity = new DefectServiceRequest();
        defectEntity.setName(name);
        defectEntity.setDescription(description);
        defectEntity.setPriority(priority);
        defectEntity.setStatus(status);
        return defectCaller.addDefectToList(defectEntity);
    }

    public ResponseEntity<DefectEntity> updateDefectList(Long id,String name,String description, DefectStatus status, DefectPriority priority){
        DefectServiceRequest request = new DefectServiceRequest();
        request.setName(name);
        request.setDescription(description);
        request.setStatus(status);
        request.setPriority(priority);
        return defectCaller.updateDefectOnList(id, request);
    }




}
