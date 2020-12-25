package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.DepotInformation;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.DepotRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ParcelService {

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, UserRepository userRepository, DepotRepository depotRepository, AuthService authService){
        this.parcelRepository= parcelRepository;
        this.depotRepository=depotRepository;
        this.authService = authService;
    }
private final ParcelRepository parcelRepository;
    private final DepotRepository depotRepository;
private final AuthService authService;

    @Transactional
    public Parcel save(Parcel parcel){
        Depot depot = new Depot();
        DepotInformation depotInformation = new DepotInformation();
        depot.setParcel(parcel);
        depot.setCreated(Instant.now());
        depot.setUser( authService.getUser().orElseThrow());
        depotInformation.setId(depot.getUser().getDepotInformation().getId());
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
}
