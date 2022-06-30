package com.warehouse.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.entity.Parcel;
import com.warehouse.repository.PaymentRepository;
import com.warehouse.enumeration.ParcelStatus;
import com.warehouse.mapper.PaymentMapper;
import com.warehouse.entity.PaymentInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {

    private final APIContext apiContext;
    private final PaymentRepository paymentRepository;
    private final String SUCCESS_URL = "/pay/success";
    private final String CANCEL_URL = "/pay/cancel";

    private final PaymentMapper mapper;

    private final String url = "https://inparcel.herokuapp.com";

    public Payment createPayment(
            String description,
            String cancelUrl,
            String successUrl,
            Parcel parcel) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency("PLN");
        amount.setTotal(String.format("%.2f", parcel.getPrice()));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("ORDER");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    public String payment(Parcel parcel) throws PayPalRESTException {
        PaymentInformation paymentInformation = new PaymentInformation();
        Payment payment = createPayment("Payment for parcel: "
                        + parcel.getId(),
                url + "/api/payments" + CANCEL_URL,
                url + "/api/payments" + SUCCESS_URL,
                         parcel);
        paymentInformation.setParcel(parcel);
        paymentInformation.setParcelStatus(ParcelStatus.NOT_PAID);
        paymentInformation.setPaypalId(payment.getId());
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                paymentInformation.setPaymentUrl(link.getHref());
                paymentRepository.save(paymentInformation);
                return link.getHref();
            }
        }
        return "";
    }

    public String successPay(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = executePayment(paymentId, payerId);
        PaymentInformation paymentInformationToUpdate = paymentRepository.findByPaypalId(payment.getId());
        if (payment.getState().equals("approved")) {
            paymentInformationToUpdate.setParcelStatus(ParcelStatus.PAID);
            paymentRepository.save(paymentInformationToUpdate);
            return "Płatność powiodła się";
        }
        return "Płatność nie powiodła się";
    }

    public List<PaymentInformation> getAll() {
        return paymentRepository.findAll();
    }

    public String cancelPayment() {
        return "Płatność została anulowana";
    }

    public PaymentInformation findByParcelId(UUID id) {
        return paymentRepository.findByParcel_id(id);
    }
}
