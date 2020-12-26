package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepotRepository extends JpaRepository<Depot, Long> {

    List<Depot> findAll();
    Optional<Depot> findById(Long id);

    @Query("SELECT id from Depot where depotCode=:depotCode")
    Long depot(String depotCode);
}
