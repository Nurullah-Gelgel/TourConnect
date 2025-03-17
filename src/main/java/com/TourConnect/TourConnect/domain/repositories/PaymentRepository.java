package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository  {

    Payment save(Payment payment);
    Optional<Payment> findById(UUID id);
    Optional<Payment> findByTransactionId(String id);
    void deleteById(UUID id);
    void deleteAll();
    List<Payment> findAll();
}
