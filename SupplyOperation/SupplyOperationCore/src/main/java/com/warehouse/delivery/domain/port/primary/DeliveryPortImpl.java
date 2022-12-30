package com.warehouse.delivery.domain.port.primary;

import com.warehouse.delivery.domain.model.SupplyInformation;
import com.warehouse.delivery.domain.model.SupplyResponse;
import com.warehouse.delivery.domain.port.secondary.DeliveryServicePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliveryPortImpl implements DeliveryPort {

    private final DeliveryServicePort servicePort;

    @Override
    public SupplyResponse deliver(SupplyInformation supplyInformation) {
        return servicePort.deliver(supplyInformation);
    }
}
