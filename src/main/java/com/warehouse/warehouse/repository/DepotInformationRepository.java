package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.DepotInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepotInformationRepository extends JpaRepository<DepotInformation, Long> {


    List<DepotInformation> findDepotInformationById(Long id);

}
