package com.warehouse.parcelmanagement.reroute.infrastructure.api;

import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.*;

public interface RerouteService {

    RerouteResponseDto sendReroutingInformation(RerouteRequestDto rerouteRequest);

    ParcelResponseDto update(UpdateParcelRequestDto request);

    RerouteTokenResponseDto findByToken(TokenDto token);

    ParcelResponseDto loadByParcelId(Long parcelId);

    RerouteTokenResponseDto loadByTokenAndParcelId(TokenDto token, ParcelIdDto parcelId);
}
