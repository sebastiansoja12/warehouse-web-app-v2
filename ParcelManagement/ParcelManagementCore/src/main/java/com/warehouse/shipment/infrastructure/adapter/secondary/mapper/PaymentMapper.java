package com.warehouse.shipment.infrastructure.adapter.secondary.mapper;

import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.domain.model.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaymentMapper {

    PaymentRequest map(com.warehouse.shipment.domain.vo.PaymentRequest request);

    @Mapping(source = "paymentUrl", target = "link.paymentUrl")
    PaymentResponse map(com.warehouse.shipment.domain.vo.PaymentResponse paymentResponse);
}
