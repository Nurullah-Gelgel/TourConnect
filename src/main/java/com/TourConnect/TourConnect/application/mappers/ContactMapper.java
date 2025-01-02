package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ContactDto;
import com.TourConnect.TourConnect.domain.entities.Contact;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ContactMapper {

    Contact toEntity(ContactDto contactDto);
    ContactDto toDto(Contact contact);
}
