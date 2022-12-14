package com.warehouse.addressdetermination.infrastructure.primary.mapper;

import com.warehouse.addressdetermination.domain.model.Coordinates;
import com.warehouse.depot.api.dto.CoordinatesDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressRequestMapper {
    Coordinates map(CoordinatesDto coordinatesDto);
}
