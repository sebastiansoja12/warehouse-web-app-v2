package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.DepotInformation;
import com.warehouse.warehouse.repository.DepotInformationRepository;
import com.warehouse.warehouse.repository.DepotRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class DepotInformationService {

    private final DepotInformationRepository depotInformationRepository;


    public List<DepotInformation> saveAll(List<DepotInformation> depotInformationList){
         return depotInformationRepository.saveAll(depotInformationList);
    }

    public List<DepotInformation> getAll(){
        return depotInformationRepository.findAll();
    }


}
