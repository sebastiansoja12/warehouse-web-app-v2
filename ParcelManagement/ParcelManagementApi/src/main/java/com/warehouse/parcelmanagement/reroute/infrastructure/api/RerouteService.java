package com.warehouse.parcelmanagement.reroute.infrastructure.api;

import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.*;

public interface RerouteService {

    RerouteResponseDto sendReroutingInformation(RerouteRequestDto rerouteRequest);

    ParcelResponseDto update(UpdateParcelRequestDto parcelDto);

    RerouteTokenResponseDto findByToken(TokenDto tokenDto);

    RerouteTokenResponseDto loadByTokenAndParcelId(TokenDto tokenDto, ParcelIdDto parcelId);
}
