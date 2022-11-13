package com.warehouse.shipment.domain.port.secondary;

import com.warehouse.shipment.domain.model.Parcel;

public interface ShipmentRepository {

    Long save(Parcel parcel);

    void delete(Long parcelId);

    Parcel loadParcelById(Long parcelId);
}
