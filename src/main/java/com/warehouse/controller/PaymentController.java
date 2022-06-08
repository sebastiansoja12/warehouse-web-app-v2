package com.warehouse.controller;

import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.model.Parcel;
import com.warehouse.model.PaymentInformation;
import com.warehouse.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public String payment(@RequestBody Parcel parcel) throws PayPalRESTException {
        return paymentService.payment(parcel);
    }

    @GetMapping(value = "/pay/cancel")
    public String cancelPay() {
        return paymentService.cancelPayment();
    }

    @GetMapping(value = "/pay/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId)
            throws PayPalRESTException {
        return paymentService.successPay(paymentId, payerId);
    }

    @GetMapping("/{id}")
    public PaymentInformation findPaymentByParcelId(@PathVariable("id") UUID id) {
        return paymentService.findByParcelId(id);
    }

    @GetMapping()
    public List<PaymentInformation> getPayments() {
        return paymentService.getAll();
    }

}