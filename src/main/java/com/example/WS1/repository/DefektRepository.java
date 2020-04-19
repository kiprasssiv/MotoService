package com.example.WS1.repository;

import com.example.WS1.model.Defekt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DefektRepository extends JpaRepository<Defekt, UUID> {
    public abstract Optional<Defekt> getDefektById(UUID id);
    public abstract List<Defekt> findAll();
}