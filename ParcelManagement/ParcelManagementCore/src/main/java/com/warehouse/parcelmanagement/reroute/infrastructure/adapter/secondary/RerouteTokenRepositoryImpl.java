package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RerouteMapper;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RerouteTokenRepositoryImpl implements RerouteTokenRepository {

    private final RerouteMapper rerouteMapper;

    private final RerouteTokenReadRepository readRepository;

    @Override
    public Optional<RerouteTokenEntity> loadRerouteTokenByParcelIdAndToken(String parcelId, Integer token) {
        return readRepository.loadRerouteTokenByParcelIdAndToken(parcelId, token);
    }
}
