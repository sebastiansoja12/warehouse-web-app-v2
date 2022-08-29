package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface RequestMapper {

    RerouteRequest map(RerouteRequestDto rerouteRequestDto);

    RerouteRequestDto map(RerouteRequest rerouteRequest);
}
