package com.warehouse.paypal.infrastructure.adapter.secondary.mapper;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.warehouse.paypal.domain.model.LinkInformation;
import com.warehouse.paypal.domain.model.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface PaypalResponseMapper {

    @Mapping(source = "href", target = "paymentUrl")
    LinkInformation map(Links links);

    default PaymentResponse map(Payment payment) {
        final LinkInformation linkInformation = new LinkInformation();
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                linkInformation.setPaymentUrl(link.getHref());
            }
        }
        return PaymentResponse.builder()
                .link(linkInformation)
                .createTime(payment.getCreateTime())
                .paymentMethod(payment.getPayer().getPaymentMethod())
                .failureReason(payment.getFailureReason())
                .build();
    }
}
