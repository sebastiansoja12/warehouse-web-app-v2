package com.warehouse.parcelmanagement.reroute.domain.port.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteToken;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;

import java.util.List;
import java.util.Optional;

public interface RerouteTokenRepository {

    List<RerouteToken> loadRerouteTokenByParcelIdAndToken(String parcelId, Integer token);

    Optional<RerouteTokenResponse> findByToken(Integer token);
}
