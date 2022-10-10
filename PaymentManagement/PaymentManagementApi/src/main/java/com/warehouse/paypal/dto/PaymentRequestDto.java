package com.warehouse.paypal.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    Long parcelId;
    double price;
}
