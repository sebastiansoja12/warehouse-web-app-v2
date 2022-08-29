package com.warehouse.parcelmanagement.reroute.domain.port.primary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;

public interface RerouteTokenPort {
    RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest);
}
