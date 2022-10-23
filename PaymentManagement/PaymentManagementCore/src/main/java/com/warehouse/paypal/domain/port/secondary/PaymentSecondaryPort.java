package com.warehouse.paypal.domain.port.secondary;

import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.domain.model.PaymentResponse;

public interface PaymentSecondaryPort {

    String update(String paymentId, String payerId);

    PaymentResponse payment(PaymentRequest request);
}
