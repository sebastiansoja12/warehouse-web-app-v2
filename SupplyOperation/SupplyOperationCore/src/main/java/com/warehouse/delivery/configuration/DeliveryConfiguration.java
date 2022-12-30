package com.warehouse.delivery.configuration;

import com.warehouse.delivery.DeliveryService;
import com.warehouse.delivery.domain.port.primary.DeliveryPort;
import com.warehouse.delivery.domain.port.primary.DeliveryPortImpl;
import com.warehouse.delivery.domain.port.secondary.DeliveryServicePort;
import com.warehouse.delivery.infrastructure.adapter.primary.DeliveryServiceAdapter;
import com.warehouse.delivery.infrastructure.adapter.primary.mapper.DeliveryRequestMapper;
import com.warehouse.delivery.infrastructure.adapter.primary.mapper.DeliveryResponseMapper;
import com.warehouse.delivery.infrastructure.adapter.secondary.DeliveryAdapter;
import com.warehouse.delivery.infrastructure.adapter.secondary.mapper.SupplyMapper;
import com.warehouse.route.infrastructure.api.RouteLogEventPublisher;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryConfiguration {

    @Bean
    public DeliveryService deliveryService(DeliveryPort deliveryPort) {
        final DeliveryRequestMapper requestMapper = Mappers.getMapper(DeliveryRequestMapper.class);
        final DeliveryResponseMapper responseMapper = Mappers.getMapper(DeliveryResponseMapper.class);
        return new DeliveryServiceAdapter(deliveryPort, requestMapper, responseMapper);
    }

    @Bean
    public DeliveryPort deliveryPort(DeliveryServicePort servicePort) {
        return new DeliveryPortImpl(servicePort);
    }

    @Bean
    public DeliveryServicePort deliveryServicePort(RouteLogEventPublisher routeLogEventPublisher) {
        final SupplyMapper supplyMapper = Mappers.getMapper(SupplyMapper.class);
        return new DeliveryAdapter(routeLogEventPublisher, supplyMapper);
    }
}
