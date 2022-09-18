package com.warehouse.parcelmanagement.reroute.domain.port.primary;

import com.warehouse.parcelmanagement.reroute.domain.model.*;
import com.warehouse.parcelmanagement.reroute.domain.service.RerouteService;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RerouteTokenPortImpl implements RerouteTokenPort {

    private final RerouteService rerouteService;

    @Override
    public ParcelResponse update(UpdateParcelRequest parcel) {
        return rerouteService.update(parcel);
    }

    @Override
    public RerouteTokenResponse findByToken(Token token) {
        return rerouteService.findByToken(token);
    }

    @Override
    public RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId parcelId) {
        return rerouteService.loadByTokenAndParcelId(token, parcelId);
    }

    @Override
    public RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest) {
        return rerouteService.saveReroutingToken(rerouteRequest.getParcelId());
    }

}
