package com.warehouse.warehouse.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.warehouse.enumeration.PaymentPass;
import com.warehouse.warehouse.model.Customer;
import com.warehouse.warehouse.repository.CustomerRepository;
import com.warehouse.warehouse.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final APIContext apiContext;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final String SUCCESS_URL = "/pay/success";
    private final String CANCEL_URL = "/pay/cancel";

    public Payment createPayment(
            Double total,
            PaymentPass pass,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency("PLN");
        total = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent(pass.name());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        // COUR-011 for now test customer, finding customers will be implemented later
        Customer customer = customerRepository.getById(44L);
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        com.warehouse.warehouse.model.Payment payment1 = com.warehouse.warehouse.model.Payment.builder()
                .paymentPass(PaymentPass.ORDER)
                .paypalId(paymentId)
                .customer(customer)
                .build();
        paymentRepository.save(payment1);
        return payment.execute(apiContext, paymentExecute);
    }

    public String payment(com.warehouse.warehouse.model.Order order) throws PayPalRESTException {
        Payment payment = createPayment(order.getPrice(), PaymentPass.ORDER, order.getDescription(),
                "http://localhost:8080/api/payments" + CANCEL_URL,
                "http://localhost:8080/api/payments" + SUCCESS_URL);
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                return "redirect:" + link.getHref();
            }
        }

        return "redirect:/";
    }

    public String successPay(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = executePayment(paymentId, payerId);
        if (payment.getState().equals("approved")) {
            return "Płatność powiodła się";
        }
        return "Płatność nie powiodła się";
    }

    public List<com.warehouse.warehouse.model.Payment> getAll() {
        return paymentRepository.findAll();
    }

    public String cancelPayment() {
        return "Płatność została anulowana";
    }
}
