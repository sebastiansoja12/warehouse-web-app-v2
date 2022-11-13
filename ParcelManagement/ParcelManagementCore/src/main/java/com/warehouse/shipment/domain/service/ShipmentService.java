package com.warehouse.shipment.domain.service;

import com.warehouse.shipment.domain.model.Parcel;
import com.warehouse.shipment.domain.model.ShipmentRequest;
import com.warehouse.shipment.domain.model.ShipmentResponse;

public interface ShipmentService {

    ShipmentResponse ship(ShipmentRequest request);

    Parcel loadParcel(Long parcelId);
}
