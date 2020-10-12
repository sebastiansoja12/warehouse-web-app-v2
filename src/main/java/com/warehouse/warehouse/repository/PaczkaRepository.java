package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Paczka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaczkaRepository extends JpaRepository<Paczka,Long> {
    Optional<Paczka> findById(UUID id);
}
