package com.TourConnect.TourConnect.infrastructure.repositories.jpa;

import com.TourConnect.TourConnect.domain.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaContactRepository extends JpaRepository<Contact, UUID> {
}
