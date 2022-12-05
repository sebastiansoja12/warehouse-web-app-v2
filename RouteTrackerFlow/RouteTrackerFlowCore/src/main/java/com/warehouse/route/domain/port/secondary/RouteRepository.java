package com.warehouse.route.domain.port.secondary;

import com.warehouse.route.domain.model.Route;
import com.warehouse.route.domain.model.RouteResponse;

import java.util.List;

public interface RouteRepository {

    List<Route> findByParcelId(Long parcelId);

    void initializeRoute(Route route);

    RouteResponse saveSupplyRoute(Route route);

    RouteResponse save(Route route);

    List<Route> findByUsername(String username);

    void deleteByParcelIdAndDepotCodeAndUsername(Long id, String depotCode, String username);
}
