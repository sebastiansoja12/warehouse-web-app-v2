package com.warehouse.shipment.infrastructure.adapter.primary.mapper;

import com.warehouse.shipment.domain.model.ShipmentRequest;
import com.warehouse.shipment.infrastructure.api.dto.ShipmentRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentRequestMapper {

    ShipmentRequest map(ShipmentRequestDto requestDto);

    ShipmentRequestDto map(ShipmentRequest request);
}
