package com.warehouse.parcelmanagement.reroute.domain.port.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;

import java.util.List;
import java.util.Optional;

public interface RerouteTokenRepository {

    List<RerouteTokenResponse> loadByTokenAndParcelId(Token token, ParcelId parcelId);

    Optional<RerouteTokenResponse> findByToken(Token token);

    RerouteResponse saveReroutingToken(Long parcelId);
}
