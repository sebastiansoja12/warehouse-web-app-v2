package com.warehouse.parcelmanagement.reroute.domain.port.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteRequest;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;

public interface RerouteTokenPort {

    ParcelResponse update(UpdateParcelRequest request);

    ParcelResponse loadByParcelId(Long parcelId);

    RerouteTokenResponse findByToken(Token token);

    RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId parcelId);

    RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest);
}
