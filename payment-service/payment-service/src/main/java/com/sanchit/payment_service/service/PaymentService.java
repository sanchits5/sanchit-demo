package com.sanchit.payment_service.service;

import com.sanchit.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
        this.paymentGateway=paymentGateway;
        this.paymentRepository=paymentRepository;
    }

    public String postPaymentLink(String orderId) {

        return null;
    }
}
