package com.example.WS1.repository;

import com.example.WS1.model.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DefectRepository extends JpaRepository<Defect, UUID> {
    public abstract Optional<Defect> getDefektById(UUID id);
    public abstract List<Defect> findAll();
}