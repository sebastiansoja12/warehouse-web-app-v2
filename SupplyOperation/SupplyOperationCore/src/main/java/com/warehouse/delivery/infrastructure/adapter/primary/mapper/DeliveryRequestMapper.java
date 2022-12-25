package com.warehouse.delivery.infrastructure.adapter.primary.mapper;

import com.warehouse.delivery.domain.model.SupplyInformation;
import com.warehouse.route.infrastructure.api.dto.SupplyInformationDto;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryRequestMapper {
    SupplyInformation map(SupplyInformationDto supplyInformationDto);
}
