package com.warehouse.route.infrastructure.api.event;

import com.warehouse.route.infrastructure.api.RouteLogEvent;
import com.warehouse.route.infrastructure.api.dto.RouteRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class RouteRequestEvent extends RouteLogBaseEvent implements RouteLogEvent {

    private final RouteRequestDto routeRequestDto;

    @Builder
    RouteRequestEvent(@NonNull RouteRequestDto routeRequestDto) {
        super();
        this.routeRequestDto = routeRequestDto;
    }
}
