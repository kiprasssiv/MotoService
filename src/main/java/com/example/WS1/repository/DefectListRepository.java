package com.example.WS1.repository;

import com.example.WS1.model.DefectEntity;
import com.example.WS1.model.Defekt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DefectListRepository extends JpaRepository<DefectEntity, UUID> {
    public abstract Optional<DefectEntity> getDefektById(UUID id);
    public abstract List<DefectEntity> findAll();
}
