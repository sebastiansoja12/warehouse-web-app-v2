package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepotRepository extends JpaRepository<Depot,Long> {
    List<Depot> findAll();

}
