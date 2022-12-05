package com.warehouse.route.infrastructure.api;

import com.warehouse.route.infrastructure.api.dto.RouteRequestDto;
import com.warehouse.route.infrastructure.api.dto.RouteResponseDto;

public interface RouteService {

    RouteResponseDto save(RouteRequestDto routeRequestDto);
}
