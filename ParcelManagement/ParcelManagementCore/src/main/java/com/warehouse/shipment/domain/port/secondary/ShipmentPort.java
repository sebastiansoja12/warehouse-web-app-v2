package com.warehouse.shipment.domain.port.secondary;

import com.warehouse.shipment.domain.model.Parcel;
import com.warehouse.shipment.domain.model.ShipmentRequest;
import com.warehouse.shipment.domain.model.ShipmentResponse;

public interface ShipmentPort {

    ShipmentResponse ship(ShipmentRequest request);

    void delete(Long parcelId);

    Parcel loadParcelById(Long parcelId);
}
