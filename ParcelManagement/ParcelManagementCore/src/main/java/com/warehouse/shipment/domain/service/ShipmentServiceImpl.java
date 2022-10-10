package com.warehouse.shipment.domain.service;

import com.warehouse.shipment.domain.model.Parcel;
import com.warehouse.shipment.domain.model.ShipmentRequest;
import com.warehouse.shipment.domain.model.ShipmentResponse;
import com.warehouse.shipment.domain.port.secondary.ShipmentPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentPort shipmentPort;

    @Override
    public ShipmentResponse ship(ShipmentRequest request) {
        return shipmentPort.ship(request);
    }

    @Override
    public Parcel loadParcel(Long parcelId) {
        return shipmentPort.loadParcelById(parcelId);
    }
}
