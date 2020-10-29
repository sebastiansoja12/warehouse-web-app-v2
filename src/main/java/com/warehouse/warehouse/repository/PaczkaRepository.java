package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Paczka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PaczkaRepository extends JpaRepository<Paczka,Long> {

    List<Paczka> findAll();
    Optional<Paczka> findByKodPaczki(String kodPaczki);
}
