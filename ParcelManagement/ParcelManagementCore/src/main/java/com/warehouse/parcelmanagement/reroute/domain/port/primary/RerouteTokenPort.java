package com.warehouse.parcelmanagement.reroute.domain.port.primary;

import com.warehouse.parcelmanagement.reroute.domain.vo.Parcel;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;

public interface RerouteTokenPort {
    RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest);

    ParcelResponse update(Parcel parcel);

    RerouteTokenResponse findByToken(Integer token);
}
