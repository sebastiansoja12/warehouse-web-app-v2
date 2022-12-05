package com.warehouse.route.infrastructure.adapter.primary;

import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.domain.model.RouteResponse;
import com.warehouse.route.domain.port.primary.RouteTrackerLogPort;
import com.warehouse.route.infrastructure.adapter.primary.mapper.RouteRequestMapper;
import com.warehouse.route.infrastructure.adapter.primary.mapper.RouteResponseMapper;
import com.warehouse.route.infrastructure.api.RouteService;
import com.warehouse.route.infrastructure.api.dto.RouteRequestDto;
import com.warehouse.route.infrastructure.api.dto.RouteResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouteLogApiAdapter implements RouteService {


    private final RouteRequestMapper routeRequestMapper;

    private final RouteResponseMapper routeResponseMapper;

    private final RouteTrackerLogPort trackerLogPort;

    @Override
    public RouteResponseDto save(RouteRequestDto routeRequestDto) {
        final RouteRequest routeRequest = routeRequestMapper.map(routeRequestDto);
        final RouteResponse response = trackerLogPort.saveRoute(routeRequest);
        return routeResponseMapper.map(response);
    }
}
