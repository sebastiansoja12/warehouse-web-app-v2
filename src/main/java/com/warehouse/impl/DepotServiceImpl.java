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

    public List<Depot> saveAll(List<Depot> depotList) {
        return depotRepository.saveAll(depotList);
    }

    public List<Depot> findAll() {
        return depotRepository.findAll();
    }


    public Optional<Depot> findById(Long id) {
        return depotRepository.findById(id);
    }
}
