package com.warehouse.shipment.infrastructure.adapter.primary.mapper;

import com.warehouse.shipment.domain.model.ShipmentResponse;
import com.warehouse.shipment.infrastructure.api.dto.ShipmentResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentResponseMapper {

    ShipmentResponse map(ShipmentResponseDto responseDto);

    ShipmentResponseDto map(ShipmentResponse response);

}
