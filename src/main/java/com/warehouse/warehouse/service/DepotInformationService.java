package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.DepotInformation;
import com.warehouse.warehouse.repository.DepotInformationRepository;
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
public class DepotInformationService {

    private final DepotInformationRepository depotInformationRepository;
    private final DepotRepository depotRepository;

    @Transactional
    public DepotInformation save(DepotInformation depotInformation){
        Depot depot = new Depot();
        depot.setDepotInformation(depotInformation);
        depotRepository.save(depot);
        return depotInformationRepository.save(depotInformation);
    }


}
