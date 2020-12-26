package com.warehouse.warehouse.service;

import com.warehouse.warehouse.exceptions.WarehouseException;
import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.Route;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.RouteRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class ParcelService {

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, UserRepository userRepository, RouteRepository routeRepository, AuthService authService){
        this.parcelRepository= parcelRepository;
        this.routeRepository = routeRepository;
        this.authService = authService;
    }
private final ParcelRepository parcelRepository;
    private final RouteRepository routeRepository;
private final AuthService authService;

    @Transactional
    public Parcel save(Parcel parcel){

            Route route = new Route();
            User user = new User();
            user.setId(1);
            route.setParcel(parcel);
            route.setCreated(Instant.now());
            route.setUser(user);
            route.setDepot(initiate());
            routeRepository.save(route);
            return parcelRepository.save(parcel);

    }

    public Depot initiate(){
        Depot depot = new Depot();
        depot.setId((long)9);
        depot.setCity("-");
        depot.setCountry("-");
        depot.setCity("-");
        depot.setStreet("-");
        return depot;
    }

    @Transactional(readOnly = true)
    public List<Parcel> findAll(){
        return parcelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parcel findById(UUID id) {
        if(parcelRepository.exists(Example.of(parcelRepository.findById(id).orElseThrow()))){
            return parcelRepository.findById(id).orElseThrow();

        }
        return null;
    }
}
