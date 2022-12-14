package com.warehouse.depot.infrastructure.primary.mapper;

import com.warehouse.depot.api.dto.DepotDto;
import com.warehouse.depot.domain.model.Depot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DepotResponseMapper {

    DepotDto map(Depot depot);

    List<DepotDto> map(List<Depot> depots);
}
