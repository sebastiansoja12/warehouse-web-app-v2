package com.warehouse.warehouse.service;


import com.warehouse.warehouse.exceptions.ParcelNotFound;
import com.warehouse.warehouse.exceptions.WarehouseException;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.model.Route;
import com.warehouse.warehouse.repository.DepotRepository;
import com.warehouse.warehouse.repository.RouteRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class RouteService {

    private final ParcelRepository parcelRepository;
    private final RouteRepository routeRepository;
    private final AuthService authService;
    private final DepotRepository depotRepository;
    private final UserRepository userRepository;



    public Route save(Route route){
        Parcel parcel = parcelRepository.findById(route.getParcel().getId()).orElseThrow();



        if(routeRepository.findByParcel_IdAndUser(route.getParcel().getId(),
                authService.getCurrentUser().orElseThrow()) != null){

            throw new WarehouseException("Paczka została już zarejestrowana w tym oddziale");
        }



        route.setUser(authService.getCurrentUser().orElseThrow(()
                -> new WarehouseException("Nie znaleziono uzytkownika")));
        parcel.setCustom(route.getParcel().isCustom());
        route.setParcel(parcel);
        route.setCreated(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))));
        route.setDepot(depotRepository.findById(
                route.getUser().getDepot().getId()).orElseThrow(null));
        parcelRepository.saveAndFlush(parcel);
        return routeRepository.save(route);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Route> findAllRoutes() {
        return  routeRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Route> findByParcelId(UUID id) throws Exception {
        parcelRepository.findById(id).orElseThrow(
                () -> new ParcelNotFound("Paczka nie zostala znaleziona lub jest jeszcze nie nadana"));
        return routeRepository.findAllByParcel_IdOrderByCreatedDesc(id).orElseThrow(
                () -> new ParcelNotFound("Paczka nie zostala znaleziona lub jest jeszcze nie nadana"));
    }
   public void deleteRouteByParcelId(UUID id) {
        Route route = new Route();
        route.setUser(authService.getCurrentUser().orElseThrow(null));
        String depotCode = route.getUser().getDepot().getDepotCode();
        routeRepository.deleteByParcelIdAndDepot_DepotCode(id, depotCode);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Route> findAllByUsername(){
        Route route = new Route();
        route.setUser(authService.getCurrentUser().orElseThrow(null));
        return routeRepository.findFirst10ByUser_usernameOrderByCreatedDesc(route.getUser().getUsername());
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Route> findRoutesByUsername(String username) {
        return routeRepository.findRoutesByUser_Username(username);
    }

}
