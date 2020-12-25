package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.DepotInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepotInformationRepository extends JpaRepository<DepotInformation, Long> {

    List<DepotInformation> findAll();
    Optional<DepotInformation> findById(Long id);

}
