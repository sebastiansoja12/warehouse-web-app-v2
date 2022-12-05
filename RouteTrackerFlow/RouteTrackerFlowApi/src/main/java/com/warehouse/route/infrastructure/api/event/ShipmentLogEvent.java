package com.warehouse.route.infrastructure.api.event;

import com.warehouse.route.infrastructure.api.RouteLogEvent;
import com.warehouse.route.infrastructure.api.dto.ShipmentRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ShipmentLogEvent extends RouteLogBaseEvent implements RouteLogEvent {

    private final ShipmentRequestDto shipmentRequest;

    @Builder
    ShipmentLogEvent(@NonNull ShipmentRequestDto shipmentRequest) {
        super();
        this.shipmentRequest = shipmentRequest;
    }
}
