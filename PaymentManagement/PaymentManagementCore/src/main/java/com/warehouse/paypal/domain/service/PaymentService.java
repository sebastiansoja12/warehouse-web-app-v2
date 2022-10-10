package com.warehouse.paypal.domain.service;

import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.paypal.domain.model.PaymentInformation;
import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.domain.model.PaymentResponse;

public interface PaymentService {
    PaymentResponse payment(PaymentRequest request);

    String update(PaymentInformation paymentInformation);
}
