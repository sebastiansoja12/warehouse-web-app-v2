package com.warehouse.paypal.infrastructure.adapter.primary.mapper;

import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.dto.PaymentRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface PrimaryRequestMapper {

    PaymentRequest map(PaymentRequestDto paymentRequest);

}
