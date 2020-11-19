package com.warehouse.warehouse.service;

import com.warehouse.warehouse.data.ParcelDataAccessService;
import com.warehouse.warehouse.dto.LoginRequest;
import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.DepotInformation;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.DepotInformationRepository;
import com.warehouse.warehouse.repository.DepotRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class ParcelService {

private final ParcelRepository parcelRepository;
private final ParcelDataAccessService parcelDataAccessService;
private final UserRepository userRepository;
private final DepotRepository depotRepository;
private final DepotInformationRepository depotInformationRepository;


    @Transactional
    public Parcel save( Parcel parcel){
        DepotInformation depotInformation = new DepotInformation();

        Depot depot = new Depot();
        depot.setCreated_at(Instant.now());
        depot.setParcel(parcel);
        depotInformation.setId((long) 1);
        depot.setDepotInformation(depotInformation);

        depotRepository.save(depot);
        return parcelRepository.save(parcel);
    }

    @Transactional(readOnly = true)
    public List<Parcel> findAll(){
        return parcelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parcel findByParcelCode(String parcelCode) {
        return parcelRepository.findByParcelCode(parcelCode).orElse(null);
    }


    @Transactional
    public void update(Parcel parcel, String kod) {

    }
}
