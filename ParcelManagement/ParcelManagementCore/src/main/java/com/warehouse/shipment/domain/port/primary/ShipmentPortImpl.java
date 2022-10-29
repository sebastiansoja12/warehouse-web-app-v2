package com.warehouse.shipment.domain.port.primary;

import com.warehouse.shipment.domain.model.Parcel;
import com.warehouse.shipment.domain.model.ShipmentRequest;
import com.warehouse.shipment.domain.model.ShipmentResponse;
import com.warehouse.shipment.domain.service.ShipmentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShipmentPortImpl implements ShipmentPort {

    private final ShipmentService service;

    @Override
    public ShipmentResponse ship(ShipmentRequest request) {
        return service.ship(request);
    }

    @Override
    public Parcel loadParcel(Long parcelId) {
        return service.loadParcel(parcelId);
    }
}
