package com.warehouse.delivery.infrastructure.adapter.secondary;

import com.warehouse.delivery.domain.model.SupplyInformation;
import com.warehouse.delivery.domain.model.SupplyResponse;
import com.warehouse.delivery.domain.port.secondary.DeliveryServicePort;
import com.warehouse.delivery.infrastructure.adapter.secondary.mapper.SupplyMapper;
import com.warehouse.route.infrastructure.api.RouteLogEventPublisher;
import com.warehouse.route.infrastructure.api.dto.SupplyInformationDto;
import com.warehouse.route.infrastructure.api.event.SupplyLogEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliveryAdapter implements DeliveryServicePort {


    private final RouteLogEventPublisher routeLogEventPublisher;

    private final SupplyMapper supplyMapper;

    @Override
    public SupplyResponse deliver(SupplyInformation supplyInformation) {
        final SupplyInformationDto supplyInformationDto = supplyMapper.map(supplyInformation);
        sendEvent(buildEvent(supplyInformationDto));
        return supplyMapper.mapToSupplyResponse(supplyInformation);
    }



    public SupplyLogEvent buildEvent(SupplyInformationDto supplyInformation) {
        return SupplyLogEvent.builder()
                .supplyInformation(supplyInformation)
                .build();
    }

    public void sendEvent(SupplyLogEvent event) {
        routeLogEventPublisher.send(event);
    }
}
