package com.warehouse.paypal.infrastructure.adapter.primary.mapper;

import com.warehouse.paypal.domain.model.PaymentResponse;
import com.warehouse.paypal.dto.PaymentResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface PrimaryResponseMapper {

    PaymentResponseDto map(PaymentResponse response);
}
