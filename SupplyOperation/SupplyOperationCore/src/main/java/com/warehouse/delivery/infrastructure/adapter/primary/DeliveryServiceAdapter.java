package com.warehouse.delivery.infrastructure.adapter.primary;

import com.warehouse.delivery.DeliveryService;
import com.warehouse.delivery.domain.model.SupplyInformation;
import com.warehouse.delivery.domain.model.SupplyResponse;
import com.warehouse.delivery.domain.port.primary.DeliveryPort;
import com.warehouse.delivery.infrastructure.adapter.primary.mapper.DeliveryRequestMapper;
import com.warehouse.delivery.infrastructure.adapter.primary.mapper.DeliveryResponseMapper;
import com.warehouse.route.infrastructure.api.dto.SupplyInformationDto;
import com.warehouse.supplier.dto.SupplyResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliveryServiceAdapter implements DeliveryService {


    private final DeliveryPort deliveryPort;

    private final DeliveryRequestMapper requestMapper;

    private final DeliveryResponseMapper responseMapper;


    @Override
    public SupplyResponseDto deliver(SupplyInformationDto supplyInformationDto) {
        final SupplyInformation supplyInformation = requestMapper.map(supplyInformationDto);
        final SupplyResponse supplyResponse = deliveryPort.deliver(supplyInformation);
        return responseMapper.map(supplyResponse);
    }
}
