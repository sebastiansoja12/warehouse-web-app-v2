package com.warehouse.delivery.infrastructure.adapter.primary.mapper;

import com.warehouse.delivery.domain.model.SupplyResponse;
import com.warehouse.supplier.dto.SupplyResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryResponseMapper {
    SupplyResponseDto map(SupplyResponse supplyResponse);
}
