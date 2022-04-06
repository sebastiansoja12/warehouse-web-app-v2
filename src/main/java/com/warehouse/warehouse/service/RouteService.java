package com.warehouse.warehouse.service;


import com.warehouse.warehouse.exceptions.ParcelNotFound;
import com.warehouse.warehouse.exceptions.WarehouseException;
import com.warehouse.warehouse.model.*;
import com.warehouse.warehouse.repository.DepotRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import com.warehouse.warehouse.repository.RouteRepository;
import com.warehouse.warehouse.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RouteService {

    private final ParcelRepository parcelRepository;
    private final RouteRepository routeRepository;
    private final AuthService authService;
    private final DepotRepository depotRepository;
    private final SupplierRepository supplierRepository;
    private List<Route> temporaryRouteList;

    @Transactional
    public List<Route> temporarySave(Route route){
        Parcel parcel = parcelRepository.findById(route.getParcel().getId()).orElseThrow();
        Supplier supplier = supplierRepository.findBySupplierCode(route.getSupplier().getSupplierCode());

        if (routeRepository.findByParcel_IdAndUser(route.getParcel().getId(),
                authService.getCurrentUser().orElseThrow()) != null) {
            throw new WarehouseException("Paczka została już zarejestrowana w tym oddziale");
        }

        route.setUser(getCurrentUser());
        parcel.setCustom(route.getParcel().isCustom());
        route.setSupplier(supplier);
        route.setParcel(parcel);
        route.setCreated(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))));
        route.setDepot(findUsersDepot());
        route.setId(UUID.randomUUID());

        temporaryRouteList.add(route);
        return temporaryRouteList;
    }

    public User getCurrentUser(){
        return authService.getCurrentUser().orElseThrow(() -> new WarehouseException("Nie znaleziono uzytkownika"));
    }

    public Depot findUsersDepot(){
        return depotRepository.findById(getCurrentUser().getDepot().getId()).orElseThrow(null);
    }

    public void save() {
        List<Route> temporaryRoutesByUser = temporaryRouteList
                .stream()
                .filter(u -> u.getUser().getUsername().equals(getCurrentUser().getUsername()))
                .collect(Collectors.toList());
        routeRepository.saveAll(temporaryRoutesByUser);
        temporaryRoutesByUser.removeIf(u -> u.getUser().getUsername().equals(getCurrentUser().getUsername()));
        temporaryRouteList.removeIf(u -> u.getUser().getUsername().equals(getCurrentUser().getUsername()));
    }

    public List<Route> findAllTemporaryRoutesByUsername() {
        return temporaryRouteList
                .stream()
                .filter(p -> p.getUser().equals(getCurrentUser()))
                .collect(Collectors.toList());
    }

    public List<Route> findAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Route> findByParcelId(UUID id) {
        parcelRepository.findById(id).orElseThrow(() -> new ParcelNotFound("Paczka nie znaleziona"));
        return routeRepository.findAllByParcel_IdOrderByCreated(id).orElseThrow(
                () -> new ParcelNotFound("Paczka nie zostala znaleziona lub jest jeszcze nie nadana"));
    }

    public void deleteRouteByParcelId(UUID id) {
        Route route = new Route();
        route.setUser(getCurrentUser());
        String depotCode = getCurrentUser().getDepot().getDepotCode();
        routeRepository.deleteByParcelIdAndDepot_DepotCode(id, depotCode);
    }

    public List<Route> findAllByUsername() {
        Route route = new Route();
        route.setUser(getCurrentUser());
        return routeRepository.findByUser_username(route.getUser().getUsername());
    }

}
