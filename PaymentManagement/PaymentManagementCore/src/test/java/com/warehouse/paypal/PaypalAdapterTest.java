package com.warehouse.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.sdk.info.SDKVersionImpl;
import com.warehouse.paypal.domain.enumeration.ParcelStatus;
import com.warehouse.paypal.domain.model.*;
import com.warehouse.paypal.domain.port.secondary.PaymentRepository;
import com.warehouse.paypal.infrastructure.adapter.secondary.PaypalAdapter;
import com.warehouse.paypal.infrastructure.adapter.secondary.mapper.PaypalMapper;
import com.warehouse.paypal.infrastructure.adapter.secondary.mapper.PaypalResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaypalAdapterTest {


    @Mock
    private PaypalMapper paypalMapper;

    @Mock
    private PaypalResponseMapper responseMapper;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private  PaypalAdapter paypalAdapter;

    private final static Long PARCEL_ID = 100001L;

    private final static double PRICE = 25;

    private final String accessToken = "accessToken";


    @Test
    @Disabled
    void shouldCreatePayment() throws PayPalRESTException {
        // given
        final PaymentRequest request = new PaymentRequest();
        request.setParcelId(PARCEL_ID);
        request.setPrice(PRICE);


        final Amount amount = new Amount();
        amount.setTotal("1");
        amount.setCurrency("PLN");

        final APIContext apiContext = new APIContext("accessToken");
        final Payer payer =  getPayer();
        final List<Transaction> transactions = transactions(request.getParcelId(), amount);

        final RedirectUrls redirectUrls = getRedirectUrls();
        final Payment expectedPayment = getPayment(redirectUrls, transactions, payer);
        final PaymentResponse expected = buildPaymentResponse();


        // when
        when(expectedPayment.create(apiContext)).thenReturn(expectedPayment);
        final PaymentResponse paymentResponse = paypalAdapter.payment(request);

        // then
        assertThat(paymentResponse).isNotNull();
        assertThat(expected.getCreateTime()).isEqualTo(paymentResponse.getCreateTime());
    }

    private Payment buildPayment() {
        final Payment payment = new Payment();
        payment.setCreateTime("now");
        payment.setFailureReason(null);
        payment.setId("1");
        payment.setPayer(new Payer());
        payment.setRedirectUrls(new RedirectUrls());
        return payment;
    }

    private PaymentResponse buildPaymentResponse() {
        return PaymentResponse.builder()
                .failureReason(null)
                .paymentMethod("paypal")
                .createTime("now")
                .link(link())
                .build();
    }

    private LinkInformation link() {
        final LinkInformation linkInformation = new LinkInformation();
        linkInformation.setPaymentUrl("test.pl");
        return linkInformation;
    }

    private Payment getPayment(RedirectUrls redirectUrls, List<Transaction> transactions, Payer payer) {
        final Payment payment = new Payment();
        payment.setIntent("ORDER");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);
        return payment;
    }

    private RedirectUrls getRedirectUrls() {
        final RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("/payment/success");
        redirectUrls.setReturnUrl("/payment/cancel");
        return redirectUrls;
    }

    private Payer getPayer() {
        final Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        return payer;
    }

    public List<Transaction> transactions(Long parcelId, Amount amount) {
        final Transaction transaction = new Transaction();
        transaction.setDescription("Payment for parcel: " + parcelId);
        transaction.setAmount(amount);

        final List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        return transactions;
    }


}
