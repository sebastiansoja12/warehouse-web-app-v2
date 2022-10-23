package com.warehouse.paypal.mapper;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.warehouse.paypal.domain.model.LinkInformation;
import com.warehouse.paypal.domain.model.PaymentResponse;
import com.warehouse.paypal.infrastructure.adapter.secondary.mapper.PaypalResponseMapper;
import com.warehouse.paypal.infrastructure.adapter.secondary.mapper.PaypalResponseMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaypalResponseMapperTest {

    private PaypalResponseMapper paypalResponseMapper;

    @BeforeEach
    void setUp() {
        paypalResponseMapper = new PaypalResponseMapperImpl();
    }

    @Test
    void shouldMapFromLinksToLinkInformation() {
        // given
        final Links links = new Links();
        links.setHref("test.pl");
        // when
        final LinkInformation linkInformation = paypalResponseMapper.map(links);
        // then
        assertThat(linkInformation.getPaymentUrl()).isEqualTo(links.getHref());
    }

    @Test
    void shouldMapFromPaymentToPaymentResponse() {
        // given
        final Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        final Links links = new Links();
        links.setHref("test.pl");
        links.setRel("test");

        final List<Links> linksList = new ArrayList<>();
        linksList.add(links);

        final LinkInformation linkInformation = new LinkInformation();
        linkInformation.setPaymentUrl("test.pl");

        final Payment payment = new Payment();
        payment.setCreateTime("now");
        payment.setLinks(linksList);
        payment.setFailureReason("none");
        payment.setPayer(payer);
        // when
        final PaymentResponse paymentResponse = paypalResponseMapper.map(payment);
        // then
        assertThat(paymentResponse.getCreateTime()).isEqualTo(payment.getCreateTime());
    }
}
