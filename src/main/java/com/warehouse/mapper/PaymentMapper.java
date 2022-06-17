package com.warehouse.mapper;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.warehouse.entity.PaymentInformation;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper {
    default PaymentInformation map(Payment payment) {
        PaymentInformation paymentInformation = new PaymentInformation();
        paymentInformation.setPaypalId(payment.getId());
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                paymentInformation.setPaymentUrl(payment.getLinks().get(0).getHref());
            }
        }
        return paymentInformation;
    }

    String map(RedirectUrls value);
}
