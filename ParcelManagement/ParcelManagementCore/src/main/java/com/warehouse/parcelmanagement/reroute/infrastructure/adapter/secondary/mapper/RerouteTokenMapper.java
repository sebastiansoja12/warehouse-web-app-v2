package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteToken;
import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RerouteTokenMapper {

    RerouteToken map(RerouteTokenEntity entity);

    RerouteTokenEntity map(RerouteToken rerouteToken);

    @Mapping(source = "parcelId", target = "parcelId")
    RerouteResponse mapToRerouteResponse(RerouteTokenEntity rerouteToken);

    RerouteTokenEntity map(UpdateParcelRequest parcelRequest);

    @Mapping(source = "token", target = "token")
    @Mapping(source = "parcelId", target = "parcelId")
    @Mapping(source = "createdDate", target = "created")
    @Mapping(source = "expiryDate", target = "timeout")
    RerouteTokenResponse mapToResponse(RerouteTokenEntity entity);
}
