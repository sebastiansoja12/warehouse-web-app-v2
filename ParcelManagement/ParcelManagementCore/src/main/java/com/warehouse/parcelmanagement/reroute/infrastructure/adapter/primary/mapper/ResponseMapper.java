package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface ResponseMapper {
    RerouteResponseDto map(RerouteResponse rerouteResponse);
}
