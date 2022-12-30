package com.warehouse.delivery.domain.port.primary;

import com.warehouse.delivery.domain.model.SupplyInformation;
import com.warehouse.delivery.domain.model.SupplyResponse;

public interface DeliveryPort {

    SupplyResponse deliver(SupplyInformation supplyInformation);
}
