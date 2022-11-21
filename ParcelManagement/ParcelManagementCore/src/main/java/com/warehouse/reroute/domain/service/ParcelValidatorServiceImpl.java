package com.warehouse.reroute.domain.service;

import com.warehouse.reroute.infrastructure.adapter.secondary.ParcelShipmentReadRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParcelValidatorServiceImpl implements ParcelValidatorService {

    private final ParcelShipmentReadRepository repository;

    @Override
    public boolean validate(Long id) {
        return repository.findById(id).isPresent();
    }
}
