package com.warehouse.auth.infrastructure.adapter.secondary;

import com.warehouse.auth.domain.model.Depot;
import com.warehouse.auth.domain.port.secondary.DepotRepository;
import com.warehouse.auth.infrastructure.adapter.secondary.mapper.DepotEntityMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DepotRepositoryImpl implements DepotRepository {

    private final DepotReadRepository repository;

    private final DepotEntityMapper mapper;

    @Override
    public Depot findDepotByCode(String depotCode) {
        return mapper.map(repository.findByDepotCode(depotCode));
    }
}
