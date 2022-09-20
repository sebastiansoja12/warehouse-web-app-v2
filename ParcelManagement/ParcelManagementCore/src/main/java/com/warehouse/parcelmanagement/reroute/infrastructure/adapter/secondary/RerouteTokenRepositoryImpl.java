package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.RerouteResponse;
import com.warehouse.parcelmanagement.reroute.domain.model.RerouteToken;
import com.warehouse.parcelmanagement.reroute.domain.model.Token;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelId;
import com.warehouse.parcelmanagement.reroute.domain.vo.RerouteTokenResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.ParcelMapper;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.RerouteTokenMapper;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RerouteTokenRepositoryImpl implements RerouteTokenRepository {

    private final RerouteTokenMapper rerouteTokenMapper;

    private final RerouteTokenReadRepository rerouteTokenReadRepository;

    @Override
    public List<RerouteTokenResponse> loadByTokenAndParcelId(Token token, ParcelId parcelId) {
        return rerouteTokenReadRepository.loadByTokenAndParcelId(token.getValue(), parcelId.getParcelId())
                .stream().map(rerouteTokenMapper::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<RerouteTokenResponse> findByToken(Token token) {
        return rerouteTokenReadRepository.findByToken(token.getValue())
                .stream().map(rerouteTokenMapper::mapToResponse).findFirst();
    }

    @Override
    public RerouteResponse saveReroutingToken(Long parcelId) {
        return rerouteTokenMapper.mapToRerouteResponse(rerouteTokenReadRepository.save(generateRerouteToken(parcelId)));
    }

    private RerouteTokenEntity generateRerouteToken(Long parcelId) {
        final RerouteToken rerouteToken = new RerouteToken();
        rerouteToken.setToken(rerouteToken.generateToken());
        rerouteToken.setCreatedDate(Instant.now());
        rerouteToken.setExpiryDate(Instant.now().plusSeconds(600L));
        rerouteToken.setParcelId(parcelId);
        return rerouteTokenMapper.map(rerouteToken);
    }


}
