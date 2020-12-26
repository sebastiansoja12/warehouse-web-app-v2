package com.warehouse.warehouse.service;


import com.warehouse.warehouse.model.Route;
import com.warehouse.warehouse.repository.DepotRepository;
import com.warehouse.warehouse.repository.RouteRepository;
import com.warehouse.warehouse.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
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



    public Route save(Route route){
        route.setParcel(parcelRepository.findById(route.getParcel().getId()).orElseThrow());
        route.setCreated(Instant.now());
        route.setUser(authService.getCurrentUser().orElseThrow(null));
        route.setDepot(depotRepository.findById(
                route.getUser().getDepot().getId()).orElseThrow(null));
        return routeRepository.save(route);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Route> findAllRoutes() {
        return  routeRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Route> findByParcelId(UUID id) {
        return routeRepository.findAllByParcel_IdOrderByCreatedDesc(id);
    }
   public void deleteRouteByParcelId( UUID id) {
        routeRepository.deleteByParcelId( id);
    }

}
