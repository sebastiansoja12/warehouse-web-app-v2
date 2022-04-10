package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {

    @Query(value = "select * from Depot order by id", nativeQuery = true)
    List<Depot> findAll();

    Optional<Depot> findById(Long id);

    @Query("SELECT id from Depot where depotCode=:depotCode and id<9")
    Long depot(String depotCode);

    @Query("SELECT id from Depot where depotCode=:depotCode")
    Long getIdByDepotCode(String depotCode);

    Optional<Depot> findByDepotCode(String depotCode);

}
