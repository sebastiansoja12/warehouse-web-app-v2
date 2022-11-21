package com.warehouse.route.domain.port.secondary;

import com.warehouse.route.domain.model.Route;
import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.domain.model.RouteResponse;

import java.util.List;

public interface RouteTrackerServicePort {

    RouteResponse saveRoute(RouteRequest routeRequest);

    List<Route> findByParcelId(Long parcelId);

    List<Route> findByUsername(String username);

    void deleteRoute(Long id);
}
