package com.warehouse.paypal.domain.port.secondary;

import com.warehouse.paypal.domain.model.PaymentInformation;

public interface PaymentRepository {

    void savePayment(PaymentInformation payment);

    void updatePayment(String paymentId);

    PaymentInformation findByPaymentId(String paypalId);
}
