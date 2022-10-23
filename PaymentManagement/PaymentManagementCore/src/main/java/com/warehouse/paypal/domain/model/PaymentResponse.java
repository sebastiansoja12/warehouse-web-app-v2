package com.warehouse.paypal.domain.model;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class PaymentResponse {
    LinkInformation link;
    String createTime;
    String failureReason;
    String paymentMethod;

}
