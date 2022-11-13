package com.warehouse.reroute.domain.port.primary;

import com.warehouse.reroute.domain.model.RerouteRequest;
import com.warehouse.reroute.domain.model.RerouteResponse;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.domain.vo.ParcelResponse;
import com.warehouse.reroute.domain.vo.RerouteTokenResponse;

public interface RerouteServicePort {

    ParcelResponse update(UpdateParcelRequest request);

    RerouteTokenResponse findByToken(Token token);

    RerouteTokenResponse loadByTokenAndParcelId(Token token, ParcelId aParcelId);

    RerouteResponse sendReroutingInformation(RerouteRequest rerouteRequest);
}
