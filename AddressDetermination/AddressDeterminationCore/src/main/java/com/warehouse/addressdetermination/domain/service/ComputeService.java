package com.warehouse.addressdetermination.domain.service;

import com.warehouse.addressdetermination.domain.model.Coordinates;
import com.warehouse.depot.api.dto.DepotDto;

import java.util.List;

public interface ComputeService {

    String computeLength(Coordinates coordinates, List<DepotDto> depots);
}
