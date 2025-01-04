package com.TourConnect.TourConnect.application.services;


import com.TourConnect.TourConnect.application.dtos.ContactDto;
import com.TourConnect.TourConnect.application.mappers.ContactMapper;
import com.TourConnect.TourConnect.domain.entities.Contact;
import com.TourConnect.TourConnect.domain.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;


    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }


    public List<ContactDto> getAllContacts() {
        return contactRepository.findAll().stream().map(contactMapper::toDto).collect(Collectors.toList());
    }

    public ContactDto getContactById(UUID id) {
        return contactRepository.findById(id).map(contactMapper::toDto).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public ContactDto createContact(ContactDto contactDto) {
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(contactDto)));
    }

    public ContactDto update(UUID id, ContactDto contactDto) {
        Contact contactToUpdate = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contactMapper.updateEntity(contactDto, contactToUpdate);
        return contactMapper.toDto(contactRepository.save(contactToUpdate));
    }

    public void deleteContact(UUID id) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    public void deleteAllContacts() {
        contactRepository.deleteAll();
    }
}
