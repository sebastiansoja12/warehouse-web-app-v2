package com.warehouse.paypal;

import com.warehouse.paypal.dto.PaymentRequestDto;
import com.warehouse.paypal.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto payment(PaymentRequestDto paymentRequestDto);

}
