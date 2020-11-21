package com.warehouse.warehouse.service;


import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.repository.DepotRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class DepotService {

    private final DepotRepository depotRepository;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Depot> findAllDepots() {
        return depotRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Depot> findAllDepotByParcelId(UUID id) {
        return depotRepository.findByParcelId(id);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Depot> findByParcelCode(String parcelCode) {
        return depotRepository.findByParcel_ParcelCode(parcelCode);
    }
}
