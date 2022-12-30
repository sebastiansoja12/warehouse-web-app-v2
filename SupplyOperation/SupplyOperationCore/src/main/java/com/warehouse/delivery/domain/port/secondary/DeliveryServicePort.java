package com.warehouse.delivery.domain.port.secondary;

import com.warehouse.delivery.domain.model.SupplyInformation;
import com.warehouse.delivery.domain.model.SupplyResponse;

public interface DeliveryServicePort {

    SupplyResponse deliver(SupplyInformation supplyInformation);
}
