package com.warehouse.delivery.infrastructure.adapter.secondary.mapper;

import com.warehouse.delivery.domain.model.SupplyInformation;
import com.warehouse.delivery.domain.model.SupplyResponse;
import com.warehouse.route.infrastructure.api.dto.SupplyInformationDto;
import org.mapstruct.Mapper;

@Mapper
public interface SupplyMapper {

    SupplyInformationDto map(SupplyInformation supplyInformation);

    SupplyResponse mapToSupplyResponse(SupplyInformation supplyInformation);
}
