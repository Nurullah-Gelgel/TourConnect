package com.TourConnect.TourConnect.application.mappers;


import com.TourConnect.TourConnect.application.dtos.UsersDto;
import com.TourConnect.TourConnect.domain.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")

public interface UsersMapper {

    Users toEntity(UsersDto usersDto);
    UsersDto toDto(Users users);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UsersDto usersDto,@MappingTarget Users users);
}
