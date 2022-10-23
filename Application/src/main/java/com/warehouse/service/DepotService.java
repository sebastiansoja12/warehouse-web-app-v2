package com.warehouse.service;

import com.warehouse.entity.Depot;
import com.warehouse.repository.DepotRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DepotService {

    private final DepotRepository depotRepository;


    public List<Depot> saveAll(List<Depot> depotList) {
        return depotRepository.saveAll(depotList);
    }

    public List<Depot> getAll() {
        return depotRepository.findAll();
    }


    public Optional<Depot> findById(Long id) {
        return depotRepository.findById(id);
    }
}
