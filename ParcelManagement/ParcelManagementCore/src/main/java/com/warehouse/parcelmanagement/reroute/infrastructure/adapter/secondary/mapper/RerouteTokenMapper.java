package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteToken;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RerouteTokenMapper {

    RerouteToken map(RerouteTokenEntity entity);

    @Mapping(source = "token", target = "token")
    RerouteTokenResponse mapToResponse(RerouteTokenEntity entity);
}
