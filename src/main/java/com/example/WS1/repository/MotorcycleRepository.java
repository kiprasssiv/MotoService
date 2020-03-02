package com.example.WS1.repository;

import com.example.WS1.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, UUID> {
    public abstract Optional<Motorcycle> getMotorcycleById(UUID id);
    public abstract List<Motorcycle> findAll();
}
