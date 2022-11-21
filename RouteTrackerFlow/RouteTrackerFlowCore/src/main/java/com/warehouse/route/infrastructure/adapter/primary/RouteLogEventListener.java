package com.warehouse.route.infrastructure.adapter.primary;

import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.domain.model.ShipmentRequest;
import com.warehouse.route.domain.port.primary.RouteTrackerLogPort;
import com.warehouse.route.domain.vo.SupplyInformation;
import com.warehouse.route.infrastructure.adapter.primary.mapper.EventMapper;
import com.warehouse.route.infrastructure.api.event.RouteRequestEvent;
import com.warehouse.route.infrastructure.api.event.RouteLogBaseEvent;
import com.warehouse.route.infrastructure.api.event.ShipmentLogEvent;
import com.warehouse.route.infrastructure.api.event.SupplyLogEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
@Component
public class RouteLogEventListener {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSSS");

    private final EventMapper eventMapper;

    private final RouteTrackerLogPort trackerLogPort;


    @EventListener
    public void handle(ShipmentLogEvent event) {
        logEvent(event);
        final ShipmentRequest shipmentRequest = eventMapper.map(event.getShipmentRequest());
        trackerLogPort.initializeRoute(shipmentRequest.getParcelId());
    }

    @EventListener
    public void handle(SupplyLogEvent event) {
        logEvent(event);
        final SupplyInformation supplyInformation = eventMapper.map(event.getSupplyInformation());
        trackerLogPort.saveSupplyRoute(supplyInformation);
    }

    @EventListener
    public void handle(RouteRequestEvent event) {
        logEvent(event);
        final RouteRequest routeRequest = eventMapper.map(event.getRouteRequestDto());
        trackerLogPort.saveRoute(routeRequest);
    }

    private void logEvent(RouteLogBaseEvent event) {
        log.info("Detected event " + event.getClass().getSimpleName() + " at " +
                event.getLocalDateTime().format(FORMATTER));
    }
}
