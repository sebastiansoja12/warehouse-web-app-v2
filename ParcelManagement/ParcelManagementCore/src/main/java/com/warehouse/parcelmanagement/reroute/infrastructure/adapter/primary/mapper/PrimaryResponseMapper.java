package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.primary.mapper;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.ParcelResponseDto;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteResponseDto;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteTokenResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface PrimaryResponseMapper {
    RerouteResponseDto map(RerouteResponse rerouteResponse);

    ParcelResponseDto map(ParcelResponse parcelResponse);

    RerouteTokenResponseDto map(RerouteTokenResponse rerouteTokenResponse);

}
