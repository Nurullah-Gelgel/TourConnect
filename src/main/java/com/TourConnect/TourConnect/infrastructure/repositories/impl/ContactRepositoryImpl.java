package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.Contact;
import com.TourConnect.TourConnect.domain.repositories.ContactRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component

public class ContactRepositoryImpl implements ContactRepository {

    private final JpaContactRepository contactRepository;

    public ContactRepositoryImpl(JpaContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        return contactRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        contactRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        contactRepository.deleteAll();
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
}
