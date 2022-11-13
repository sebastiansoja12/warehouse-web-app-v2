package com.warehouse.shipment.domain.vo;

import lombok.Data;

@Data
public class PaymentRequest {
    Long parcelId;
    double price;
}
