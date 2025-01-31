package com.sanchit.payment_service.service;

import org.springframework.stereotype.Component;

@Component
public interface PaymentGateway {
    String createPaymentLink(String orderId,String userName,String phone, int amount);
}
