package com.TourConnect.TourConnect.application.mappers;


import com.TourConnect.TourConnect.application.dtos.UsersDto;
import com.TourConnect.TourConnect.domain.entities.Users;
import com.TourConnect.TourConnect.domain.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    // Create işlemi için
    @Mapping(target = "id", ignore = true) // id'yi set etme (null kalacak)
    Users toEntity(UsersDto dto);

    // DTO'ya dönüştür
    UsersDto toDto(Users user);

    // Update işlemi için (mapstruct bunu zaten doğru yapar)
    @Mapping(target = "id", ignore = true) // id güncellenmesin
    void updateEntity(UsersDto dto, @MappingTarget Users user);
}
