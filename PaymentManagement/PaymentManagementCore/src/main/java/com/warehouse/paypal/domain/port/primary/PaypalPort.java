package com.warehouse.paypal.domain.port.primary;

import com.warehouse.paypal.domain.model.PaymentInformation;
import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.domain.model.PaymentResponse;

public interface PaypalPort {

    PaymentResponse payment(PaymentRequest request);

    String update(PaymentInformation paymentInformation);
}
