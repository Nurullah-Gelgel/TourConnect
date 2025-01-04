package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ContactDto;
import com.TourConnect.TourConnect.domain.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact toEntity(ContactDto contactDto);
    ContactDto toDto(Contact contact);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ContactDto contactDto,@MappingTarget Contact contact);
}
