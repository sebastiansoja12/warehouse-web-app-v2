package com.warehouse.paypal.domain.model;

import lombok.*;

@Data
public class PaymentRequest {
    Long parcelId;
    double price;
}
