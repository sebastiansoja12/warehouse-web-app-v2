package com.warehouse.parcelmanagement.reroute.domain.service;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
public class RerouteServiceImpl implements RerouteService {

    private final RerouteTokenPort rerouteTokenPort;

    @Override
    public ParcelResponse update(UpdateParcelRequest parcelRequest) {
        return rerouteTokenPort.update(parcelRequest);
    }

    @Override
    public ParcelResponse loadByParcelId(Long parcelId) {
        return rerouteTokenPort.loadByParcelId(parcelId);
    }

    @Override
    public RerouteTokenResponse findByToken(Token token) {
        return rerouteTokenPort.findByToken(token);
    }

    @Override
    public RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest) {
        return rerouteTokenPort.sendReroutingInformation(rerouteRequest);
    }

    @Override
    public RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId parcelId) {
        return rerouteTokenPort.loadByTokenAndParcelId(token, parcelId);
    }
}
