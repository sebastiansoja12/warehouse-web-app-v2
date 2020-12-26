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


    public List<Depot> saveAll(List<Depot> depotList){
         return depotRepository.saveAll(depotList);
    }

    public List<Depot> getAll(){
        return depotRepository.findAll();
    }


}
