package com.warehouse.delivery;

import com.warehouse.route.infrastructure.api.dto.SupplyInformationDto;
import com.warehouse.supplier.dto.SupplyResponseDto;

public interface DeliveryService {

    SupplyResponseDto deliver(SupplyInformationDto supplyInformation);
}
