package com.warehouse.reroute.infrastructure.adapter.secondary;

import com.warehouse.reroute.domain.model.RerouteToken;
import com.warehouse.reroute.domain.model.Token;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.infrastructure.adapter.entity.RerouteTokenEntity;
import com.warehouse.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import com.warehouse.reroute.infrastructure.adapter.secondary.mapper.RerouteTokenMapper;

import java.time.Instant;

public class RerouteTokenRepositoryImpl implements RerouteTokenRepository {

    private final RerouteTokenMapper rerouteTokenMapper;

    private final RerouteTokenReadRepository repository;

    public RerouteTokenRepositoryImpl(RerouteTokenMapper rerouteTokenMapper, RerouteTokenReadRepository repository) {
        this.rerouteTokenMapper = rerouteTokenMapper;
        this.repository = repository;
    }


    @Override
    public RerouteToken loadByTokenAndParcelId(Token token, ParcelId aParcelId) {
        return repository.loadByTokenAndParcelId(token.getValue(), aParcelId.getValue())
                .map(rerouteTokenMapper::map).orElseThrow( () -> new RerouteTokenNotFoundException(
                        "Reroute token was not found"
                ));
    }

    @Override
    public RerouteToken findByToken(Token token) {
        return repository.findByToken(token.getValue()).map(rerouteTokenMapper::map).orElseThrow(
                () -> new RerouteTokenNotFoundException("Reroute token was not found"));
    }

    @Override
    public Integer saveReroutingToken(Long id) {
        final RerouteTokenEntity entity = repository.save(generateRerouteToken(id));

        return entity.getToken();
    }

    @Override
    public void deleteByToken(Token token) {
        repository.deleteByToken(token.getValue());
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
