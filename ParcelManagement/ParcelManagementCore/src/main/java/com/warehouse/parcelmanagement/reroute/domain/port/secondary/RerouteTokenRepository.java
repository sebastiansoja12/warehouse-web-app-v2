package com.warehouse.parcelmanagement.reroute.domain.port.secondary;

import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;

import java.util.Optional;

public interface RerouteTokenRepository {

    Optional<RerouteTokenEntity> loadRerouteTokenByParcelIdAndToken(String parcelId, Integer token);
}
