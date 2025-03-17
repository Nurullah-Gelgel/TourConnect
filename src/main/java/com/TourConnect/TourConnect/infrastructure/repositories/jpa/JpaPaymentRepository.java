package com.TourConnect.TourConnect.infrastructure.repositories.jpa;

import com.TourConnect.TourConnect.domain.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaPaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByTransactionId(String transactionId);

}
