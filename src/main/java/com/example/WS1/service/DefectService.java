package com.example.WS1.service;

import com.example.WS1.controller.exception.DefektNotFoundException;
import com.example.WS1.controller.exception.MotorcycleNotFoundException;
import com.example.WS1.model.Defekt;
import com.example.WS1.repository.DefektRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class DefectService {
    DefektRepository defektRepository;

    public DefectService(DefektRepository defektRepository){
        this.defektRepository = defektRepository;
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
            throw new MotorcycleNotFoundException();
        }
        Defekt deff = getDefekt(id);
        defektRepository.delete(deff);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
