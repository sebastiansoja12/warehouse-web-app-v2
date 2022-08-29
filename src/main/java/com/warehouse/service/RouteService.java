package com.warehouse.service;


import com.warehouse.exceptions.WarehouseException;
import com.warehouse.entity.*;
import com.warehouse.exceptions.ParcelNotFound;
import com.warehouse.repository.DepotRepository;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.RouteRepository;
import com.warehouse.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class RouteService {

    private final ParcelRepository parcelRepository;
    private final RouteRepository routeRepository;
    private final AuthService authService;
    private final DepotRepository depotRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
    public void save(Route route){
        //final Parcel parcel = parcelRepository.getById(route.getParcel().getId());
        final Supplier supplier = supplierRepository.findBySupplierCode(route.getSupplier().getSupplierCode());


        route.setUser(getCurrentUser());
        route.setSupplier(supplier);
        //route.setParcel(parcel);
        route.setCreated(LocalDateTime.now(ZoneId.of(String.valueOf(ZoneId.systemDefault()))));
        route.setDepot(findUsersDepot());
        routeRepository.save(route);

    }

    public User getCurrentUser() {
        return authService.getCurrentUser();
    }

    public Depot findUsersDepot(){
        return depotRepository.findById(getCurrentUser().getDepot().getId()).orElseThrow(null);
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
        final Route route = new Route();
        route.setUser(getCurrentUser());
        final String depotCode = getCurrentUser().getDepot().getDepotCode();
        routeRepository.deleteByParcelIdAndDepot_DepotCode(id, depotCode);
    }

    public List<Route> findAllByUsername() {
        final Route route = new Route();
        route.setUser(getCurrentUser());
        return routeRepository.findByUser_username(route.getUser().getUsername());
    }
}
