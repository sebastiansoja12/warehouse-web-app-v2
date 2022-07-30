package com.warehouse.service;


import com.warehouse.entity.Depot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DepotService {

    List<Depot> saveAll(List<Depot> depotList);

    List<Depot> findAll();

    Optional<Depot> findById(Long id);
}
