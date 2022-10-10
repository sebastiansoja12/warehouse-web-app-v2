package com.warehouse.shipment.domain.vo;

import lombok.Data;

@Data
public class PaymentResponse {
    String paymentUrl;
    String createTime;
    String failureReason;
    String paymentMethod;
}
