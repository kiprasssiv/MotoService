package com.example.WS1.service;

import com.example.WS1.model.Motorcycle;
import com.example.WS1.repository.MotorcycleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GarageService {

    MotorcycleRepository motorcycleRepository;

    public GarageService(MotorcycleRepository motorcycleRepository){
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<Motorcycle> getMotorcycles() {
       return motorcycleRepository.findAll();
    }

    public Motorcycle getMotorcycle(UUID id) throws Exception {
        try {
            return motorcycleRepository.getMotorcycleById(id)
                    .orElseThrow(() -> new Exception("Failed to find motorcycle by id"));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Motorcycle createMotorcycle(String make, String model, int year){
        Motorcycle motorcycle = new Motorcycle(make, model,year);
        motorcycleRepository.save(motorcycle);
        return motorcycle;
    }

    public Motorcycle updateMotorcycle(UUID id, String make, String model, int year, boolean needFixing)throws Exception{
        Motorcycle motorcycle = getMotorcycle(id);
        motorcycle.setMake(make);
        motorcycle.setModel(model);
        motorcycle.setYear(year);
        motorcycle.setNeedFixing(needFixing);
        motorcycleRepository.save(motorcycle);
        return motorcycle;
    }

}
