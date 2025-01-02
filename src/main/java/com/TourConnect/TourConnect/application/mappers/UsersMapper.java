package com.TourConnect.TourConnect.application.mappers;


import com.TourConnect.TourConnect.application.dtos.UsersDto;
import com.TourConnect.TourConnect.domain.entities.Users;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component

public interface UsersMapper {

    Users toEntity(UsersDto usersDto);
    UsersDto toDto(Users users);

}
