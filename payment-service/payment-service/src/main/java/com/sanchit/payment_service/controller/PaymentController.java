package com.sanchit.payment_service.controller;

import com.sanchit.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    PaymentService paymentService;

    public  PaymentController(PaymentService paymentService)
    {
        this.paymentService=paymentService;
    }

    @PostMapping("/payment/createLink")
    public String createPaymentLink(@RequestParam String userId)
    {
        return paymentService.postPaymentLink(userId);
    }
}
