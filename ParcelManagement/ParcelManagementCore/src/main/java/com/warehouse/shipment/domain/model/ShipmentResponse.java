package com.warehouse.shipment.domain.model;

import lombok.Data;

@Data
public class ShipmentResponse {

    String paymentUrl;
    Long parcelId;

}
