package com.example.WS1.service;

import com.example.WS1.controller.exception.DefektNotFoundException;
import com.example.WS1.controller.request.DefectServiceRequest;
import com.example.WS1.model.DefectEntity;
import com.example.WS1.model.Defekt;
import com.example.WS1.model.DefektCaller;
import com.example.WS1.model.enums.DefectPriority;
import com.example.WS1.model.enums.DefectStatus;
import com.example.WS1.repository.DefektRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DefectService {
    DefektRepository defektRepository;
    DefektCaller defektCaller;



    public DefectService(DefektRepository defektRepository, DefektCaller defektCaller){
        this.defektRepository = defektRepository;
        this.defektCaller = defektCaller;
    }

    //Get defect list
    public List<DefectEntity> getDefektList() {
        List<DefectEntity> defectList;
        defectList = defektCaller.getDefectList();
        return defectList;
    }

    //Get full list
    public List<Defekt> getDefekts() {
        List<Defekt> defekts = defektRepository.findAll();
        return defekts;
    }

    //Get one deffect by id
    public Defekt getDefekt(UUID id) throws Exception {
        try {
            return defektRepository.getDefektById(id)
                    .orElseThrow(() -> new Exception("Failed to find defekt by id"));
        } catch (Exception ex) {
            throw ex;
        }
    }

    //Add a motorcycle deffect
    public ResponseEntity<Defekt> createDefekt(UUID moto_id, int def_id){
        boolean exists = false;
        List<DefectEntity> defectList;
        defectList = defektCaller.getDefectList();
        for(int i=0;i<defectList.size();i++){
            if((int)(defectList.get(i).getId()) == def_id){
                exists = true;
                break;
            }
        }
        if(exists){
            Defekt deffect = new Defekt(moto_id,def_id);
            Defekt deff = defektRepository.save(deffect);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location","/motorcycles/deffects/" + deff.getId());
            try{
                return ResponseEntity.created(new URI("/motorcycles/deffects/"+deff.getId())).header(String.valueOf(responseHeaders)).body(deffect);
            }catch(Exception e){
                throw new RuntimeException("Failed creating");
            }
        }
        else{
            throw new DefektNotFoundException();
        }
    }



    //Update defekt
    public Defekt updateDefekt(UUID id, UUID moto_id, int def_id)throws Exception{
        if(!defektRepository.existsById(id)){
            throw new DefektNotFoundException();
        }
        Defekt deffect = getDefekt(id);
        deffect.setId(id);
        deffect.setMoto_id(moto_id);
        deffect.setService_id(def_id);
        return defektRepository.save(deffect);
    }

    //Delete a finished to fix deffect
    public ResponseEntity<Defekt> deleteDefekt(UUID id) throws Exception {
        if(!defektRepository.existsById(id)){
            throw new DefektNotFoundException();
        }
        Defekt deff = getDefekt(id);
        defektCaller.removeDefect((long)deff.service_id);
        defektRepository.delete(deff);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Add a new defect to the list
    public ResponseEntity<DefectEntity> addDefectToTheList(String name, String description, DefectPriority priority, DefectStatus status){
        DefectServiceRequest defectEntity = new DefectServiceRequest();
        defectEntity.setName(name);
        defectEntity.setDescription(description);
        defectEntity.setPriority(priority);
        defectEntity.setStatus(status);
        return defektCaller.addDefectToList(defectEntity);
    }



}
