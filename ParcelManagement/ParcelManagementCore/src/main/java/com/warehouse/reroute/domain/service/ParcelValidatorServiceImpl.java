package com.warehouse.reroute.domain.service;

import com.warehouse.reroute.domain.vo.ParcelId;
import com.warehouse.reroute.infrastructure.adapter.secondary.ParcelReadRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParcelValidatorServiceImpl implements ParcelValidatorService {

    private final ParcelReadRepository repository;

    @Override
    public boolean validate(Long id) {
        return repository.findById(id).isPresent();
    }
}
