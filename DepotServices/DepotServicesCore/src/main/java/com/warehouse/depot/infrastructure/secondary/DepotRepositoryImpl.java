package com.warehouse.depot.infrastructure.secondary;

import com.warehouse.depot.domain.model.Depot;
import com.warehouse.depot.domain.model.DepotCode;
import com.warehouse.depot.domain.model.DepotId;
import com.warehouse.depot.domain.port.secondary.DepotRepository;
import com.warehouse.depot.infrastructure.secondary.entity.DepotEntity;
import com.warehouse.depot.infrastructure.secondary.exception.DepotNotFoundException;
import com.warehouse.depot.infrastructure.secondary.mapper.DepotMapper;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DepotRepositoryImpl implements DepotRepository {

    private final DepotReadRepository repository;

    private final DepotMapper depotMapper;

    @Override
    public void save(Depot depot) {
        final DepotEntity depotEntity = depotMapper.map(depot);
        repository.save(depotEntity);
    }

    @Override
    public Depot viewByCode(DepotCode depotCode) {
        return repository.findByDepotCode(depotCode.getValue()).map(depotMapper::map).orElseThrow(
                () -> new DepotNotFoundException("Depot was not found")
        );
    }

    @Override
    public Depot viewById(DepotId depotId) {
        return repository.findById(depotId.getValue()).map(depotMapper::map).orElseThrow(
                () -> new DepotNotFoundException("Depot was not found")
        );
    }

    @Override
    public List<Depot> findAll() {
        return depotMapper.map(repository.findAll());
    }
}
