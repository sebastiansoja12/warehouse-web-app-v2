package com.warehouse.route.domain.port.primary;

import com.warehouse.route.domain.model.Route;
import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.domain.model.RouteResponse;
import com.warehouse.route.domain.port.secondary.RouteLogService;
import com.warehouse.route.domain.vo.SupplyInformation;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
public class RouteTrackerLogPortImpl implements RouteTrackerLogPort {

    private final RouteLogService routeLogService;

    @Override
    public void initializeRoute(Long parcelId) {
        final Route route = Route.builder()
                .parcelId(parcelId)
                .created(LocalDateTime.now())
                .build();
        routeLogService.initializeRoute(route);
    }

    @Override
    public RouteResponse saveSupplyRoute(SupplyInformation supplyInformation) {
        final Route route = Route.builder()
                .parcelId(supplyInformation.getParcelId())
                .created(supplyInformation.created())
                .supplierCode(supplyInformation.getSupplierCode())
                .build();

        return routeLogService.saveSupplyRoute(route);
    }

    @Override
    public RouteResponse saveRoute(RouteRequest routeRequest) {
        final Route route = Route.builder()
                .parcelId(routeRequest.getParcelId())
                .created(LocalDateTime.now())
                .supplierCode(routeRequest.getSupplierCode())
                .depotCode(routeRequest.getDepotCode())
                .username(routeRequest.getUsername())
                .build();
        return routeLogService.saveRoute(route);
    }
}
