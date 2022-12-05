package com.warehouse.route.infrastructure.adapter.primary.mapper;

import com.warehouse.route.domain.model.RouteResponse;
import com.warehouse.route.infrastructure.api.dto.RouteResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface RouteResponseMapper {

    RouteResponse map(RouteResponseDto routeResponseDto);

    RouteResponseDto map(RouteResponse response);
}
