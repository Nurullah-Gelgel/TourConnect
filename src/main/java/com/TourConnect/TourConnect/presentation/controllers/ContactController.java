package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.ContactDto;
import com.TourConnect.TourConnect.application.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/getAllContacts")
    public ResponseEntity<List<ContactDto>> getContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/getContactById")
    public ResponseEntity<ContactDto> getContactById(UUID id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PostMapping("/createContact")
    public ResponseEntity<ContactDto> createContact(ContactDto contactDto) {
        return ResponseEntity.ok(contactService.createContact(contactDto));
    }

    @PutMapping("/updateContact")
    public ResponseEntity<ContactDto> updateContact(UUID id, ContactDto contactDto) {
        return ResponseEntity.ok(contactService.update(id, contactDto));
    }

    @DeleteMapping("/deleteContact")
    public ResponseEntity<Void> deleteContact(UUID id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllContacts")
    public ResponseEntity<Void> deleteAllContacts() {
        contactService.deleteAllContacts();
        return ResponseEntity.noContent().build();
    }

}
