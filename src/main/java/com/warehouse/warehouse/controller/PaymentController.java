package com.warehouse.warehouse.controller;

import com.paypal.base.rest.PayPalRESTException;
import com.warehouse.warehouse.model.Order;
import com.warehouse.warehouse.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public String payment(@RequestBody Order order) throws PayPalRESTException {
        return paymentService.payment(order);
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

    @GetMapping()
    public List<com.warehouse.warehouse.model.Payment> getPayments() {
        return paymentService.getAll();
    }

}