package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.Payment;
import com.TourConnect.TourConnect.domain.repositories.PaymentRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaPaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository jpaPaymentRepository;

    public PaymentRepositoryImpl(JpaPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return jpaPaymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return jpaPaymentRepository.findById(id);
    }

    @Override
    public Optional<Payment> findByTransactionId(String id) {
        return jpaPaymentRepository.findByTransactionId(id);
    }

    @Override
    public void deleteById(UUID id) {
        jpaPaymentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        jpaPaymentRepository.deleteAll();
    }

    @Override
    public List<Payment> findAll() {
        return jpaPaymentRepository.findAll();
    }
}
