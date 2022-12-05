package com.warehouse.route.infrastructure.adapter.primary.mapper;

import com.warehouse.route.domain.model.RouteRequest;
import com.warehouse.route.domain.model.ShipmentRequest;
import com.warehouse.route.domain.vo.SupplyInformation;
import com.warehouse.route.infrastructure.api.dto.RouteRequestDto;
import com.warehouse.route.infrastructure.api.dto.ShipmentRequestDto;
import com.warehouse.route.infrastructure.api.dto.SupplyInformationDto;
import org.mapstruct.Mapper;

@Mapper
public interface EventMapper {

    SupplyInformation map(SupplyInformationDto supplyInformationDto);

    ShipmentRequest map(ShipmentRequestDto shipmentRequestDto);

    RouteRequest map(RouteRequestDto routeRequestDto);
}
