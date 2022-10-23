package com.warehouse.paypal.domain.port.primary;

import com.warehouse.paypal.domain.model.PaymentInformation;
import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.domain.model.PaymentResponse;
import com.warehouse.paypal.domain.service.PaymentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaypalPortImpl implements PaypalPort {

    private final PaymentService paymentService;

    @Override
    public PaymentResponse payment(PaymentRequest request) {
        return paymentService.payment(request);
    }

    public String update(PaymentInformation paymentInformation) {
        return paymentService.update(paymentInformation);
    }
}
