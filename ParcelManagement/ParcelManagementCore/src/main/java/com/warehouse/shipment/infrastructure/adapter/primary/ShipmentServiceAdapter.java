package com.warehouse.shipment.infrastructure.adapter.primary;

import com.warehouse.shipment.domain.model.ShipmentRequest;
import com.warehouse.shipment.domain.model.ShipmentResponse;
import com.warehouse.shipment.domain.port.primary.ShipmentPort;
import com.warehouse.shipment.infrastructure.adapter.primary.mapper.ShipmentRequestMapper;
import com.warehouse.shipment.infrastructure.adapter.primary.mapper.ShipmentResponseMapper;
import com.warehouse.shipment.infrastructure.api.ShipmentService;
import com.warehouse.shipment.infrastructure.api.dto.ShipmentRequestDto;
import com.warehouse.shipment.infrastructure.api.dto.ShipmentResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShipmentServiceAdapter implements ShipmentService {

    private final ShipmentRequestMapper requestMapper;

    private final ShipmentResponseMapper responseMapper;

    private final ShipmentPort shipmentPort;

    @Override
    public ShipmentResponseDto ship(ShipmentRequestDto requestDto) {
        final ShipmentRequest request = requestMapper.map(requestDto);
        final ShipmentResponse response = shipmentPort.ship(request);
        return responseMapper.map(response);
    }
}
