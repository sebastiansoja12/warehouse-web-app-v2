package com.warehouse.parcelmanagement.reroute.domain.service;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;

public interface RerouteService {

    RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest);

    ParcelResponse update(UpdateParcelRequest parcelRequest);

    RerouteTokenResponse findByToken(Token token);

    RerouteResponse saveReroutingToken(Long parcelId);

    RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId parcelId);


}
