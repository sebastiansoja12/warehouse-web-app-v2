package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteToken;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RerouteTokenMapper;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RerouteTokenRepositoryImpl implements RerouteTokenRepository {

    private final RerouteTokenMapper rerouteTokenMapper;

    private final RerouteTokenReadRepository readRepository;

    @Override
    public List<RerouteToken> loadRerouteTokenByParcelIdAndToken(String parcelId, Integer token) {
        return readRepository.loadRerouteTokenByParcelIdAndToken(parcelId, token).stream().map(rerouteTokenMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RerouteTokenResponse> findByToken(Integer token) {
        return readRepository.findByToken(token).stream().map(rerouteTokenMapper::mapToResponse).findFirst();
    }
}
