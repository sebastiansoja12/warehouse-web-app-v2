package com.warehouse.route.domain.port.primary;

import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.domain.model.RouteResponse;
import com.warehouse.route.domain.vo.SupplyInformation;

public interface RouteTrackerLogPort {


    void initializeRoute(Long parcelId);

    RouteResponse saveSupplyRoute(SupplyInformation supplyInformation);


    RouteResponse saveRoute(RouteRequest routeRequest);
}
