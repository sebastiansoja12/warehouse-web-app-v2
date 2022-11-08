package com.warehouse.auth.infrastructure.adapter.secondary.mapper;

import com.warehouse.auth.domain.model.Depot;
import com.warehouse.auth.infrastructure.adapter.secondary.entity.DepotEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DepotEntityMapper {

    Depot map(DepotEntity depot);
}
