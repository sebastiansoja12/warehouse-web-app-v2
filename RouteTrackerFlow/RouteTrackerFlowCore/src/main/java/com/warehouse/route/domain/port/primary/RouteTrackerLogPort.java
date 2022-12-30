package com.warehouse.route.domain.port.primary;

import com.warehouse.route.domain.model.Route;
import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.domain.model.RouteResponse;
import com.warehouse.route.domain.vo.SupplyInformation;

import java.util.List;

public interface RouteTrackerLogPort {


    void initializeRoute(Long parcelId);

    RouteResponse saveSupplyRoute(SupplyInformation supplyInformation);

    RouteResponse saveRoute(RouteRequest routeRequest);

    List<Route> findByParcelId(Long parcelId);

    List<Route> findByUsername(String username);

    void deleteRoute(Long id);
}
