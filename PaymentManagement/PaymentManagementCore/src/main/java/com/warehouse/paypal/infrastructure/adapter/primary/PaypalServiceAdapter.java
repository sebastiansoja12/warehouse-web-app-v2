package com.warehouse.paypal.infrastructure.adapter.primary;

import com.warehouse.paypal.PaymentService;
import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.domain.model.PaymentResponse;
import com.warehouse.paypal.domain.port.primary.PaypalPort;
import com.warehouse.paypal.dto.PaymentRequestDto;
import com.warehouse.paypal.dto.PaymentResponseDto;
import com.warehouse.paypal.infrastructure.adapter.primary.mapper.PrimaryRequestMapper;
import com.warehouse.paypal.infrastructure.adapter.primary.mapper.PrimaryResponseMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaypalServiceAdapter implements PaymentService {

    private final PrimaryRequestMapper requestMapper;

    private final PrimaryResponseMapper responseMapper;

    private final PaypalPort paypalPort;

    @Override
    public PaymentResponseDto payment(PaymentRequestDto paymentRequestDto) {

        final PaymentRequest request = requestMapper.map(paymentRequestDto);

        final PaymentResponse response = paypalPort.payment(request);

        return responseMapper.map(response);
    }
}
