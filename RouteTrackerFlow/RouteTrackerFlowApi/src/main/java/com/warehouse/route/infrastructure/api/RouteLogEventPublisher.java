package com.warehouse.route.infrastructure.api;


public interface RouteLogEventPublisher {

    void send(RouteLogEvent event);
}
