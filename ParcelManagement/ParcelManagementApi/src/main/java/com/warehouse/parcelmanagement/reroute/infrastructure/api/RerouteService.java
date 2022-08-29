package com.warehouse.parcelmanagement.reroute.infrastructure.api;

import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteRequestDto;
import com.warehouse.parcelmanagement.reroute.infrastructure.api.dto.RerouteResponseDto;

public interface RerouteService {

    RerouteResponseDto sendReroutingInformation(RerouteRequestDto rerouteRequest);
}
