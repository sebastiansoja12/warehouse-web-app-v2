package com.warehouse.impl;

import com.warehouse.entity.Depot;
import com.warehouse.repository.DepotRepository;
import com.warehouse.service.DepotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepotServiceImpl implements DepotService {

    private final DepotRepository depotRepository;

    @Override
    public List<Depot> saveAll(List<Depot> depotList) {
        return depotRepository.saveAll(depotList);
    }

    @Override
    public List<Depot> findAll() {
        return depotRepository.findAll();
    }

    @Override
    public Optional<Depot> findById(Long id) {
        return depotRepository.findById(id);
    }

    @Override
    public boolean addSingleDepot(Depot depot) {
        Optional<Depot> ifGivenDepotExists = depotRepository.findByDepotCode(depot.getDepotCode());
        if (ifGivenDepotExists.isPresent()) {
            return false;
        }
        depotRepository.save(depot);
        return true;
    }
}
