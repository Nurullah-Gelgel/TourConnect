package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {

    Contact save(Contact contact);

    Optional<Contact> findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
    List<Contact> findAll();
}
