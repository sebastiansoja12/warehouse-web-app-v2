package com.warehouse.parcelmanagement.reroute.domain.port.primary;

import com.warehouse.parcelmanagement.reroute.domain.vo.Parcel;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteToken;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class RerouteTokenPortImpl implements RerouteTokenPort {

    private final RerouteTokenRepository repository;

    @Override
    public RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest) {
       return null;
    }

    @Override
    public ParcelResponse update(Parcel parcel) {
        return null;
    }

    @Override
    public RerouteTokenResponse findByToken(Integer token) {
        return repository.findByToken(token).get();
    }

    public RerouteToken generateRerouteTokenWithGivenParcel(RerouteRequest rerouteRequest) {
        return generateReroutingToken(rerouteRequest);
    }

    public RerouteToken generateReroutingToken(RerouteRequest rerouteRequest) {
        return null;
    }
}
