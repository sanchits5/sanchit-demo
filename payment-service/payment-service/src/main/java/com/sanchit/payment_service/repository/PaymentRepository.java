package com.sanchit.payment_service.repository;

import com.sanchit.payment_service.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {
}
