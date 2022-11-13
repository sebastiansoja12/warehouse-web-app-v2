package com.warehouse.shipment.infrastructure.api;

import com.warehouse.shipment.infrastructure.api.dto.ShipmentRequestDto;
import com.warehouse.shipment.infrastructure.api.dto.ShipmentResponseDto;

public interface ShipmentService {
    ShipmentResponseDto ship(ShipmentRequestDto requestDto);
}
