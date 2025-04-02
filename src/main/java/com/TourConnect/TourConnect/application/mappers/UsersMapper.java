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

public abstract class UsersMapper {

    @Autowired
    protected UserRepository usersRepository;

    public Users toEntity(UsersDto usersDto) {
        if (usersDto == null || usersDto.getId() == null) {
            return null;
        }
        return usersRepository.findById(usersDto.getId()).orElse(null);
    }

    public abstract UsersDto toDto(Users users);

    @Mapping(target = "id", ignore = true)
    public abstract void updateEntity(UsersDto usersDto, @MappingTarget Users users);
}
