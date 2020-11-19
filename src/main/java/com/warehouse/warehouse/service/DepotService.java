package com.warehouse.warehouse.service;


import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.repository.DepotRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class DepotService {

    private final DepotRepository depotRepository;

    @Transactional
    public Depot save(Depot depot){
        return depotRepository.save(depot);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Depot> findAllDepots() {
        return depotRepository.findAll();
    }
}
