package com.warehouse.reroute.domain.service;

import com.warehouse.reroute.infrastructure.adapter.secondary.RerouteTokenReadRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RerouteTokenValidatorServiceImpl implements RerouteTokenValidatorService {

    private final RerouteTokenReadRepository repository;

    @Override
    public boolean validate(Integer token) {
        return repository.findByToken(token).isPresent();
    }
}
