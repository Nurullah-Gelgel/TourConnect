package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.domain.entities.Reservation;
import com.TourConnect.TourConnect.domain.entities.Users;
import com.TourConnect.TourConnect.domain.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Mapper(componentModel = "spring", uses = {UsersMapper.class})
public abstract class ReservationMapper {

    @Autowired
    protected UserRepository usersRepository;

    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "userId", target = "users", qualifiedByName = "mapUserIdToUsers")
    public abstract Reservation toEntity(ReservationDto reservationDto);

    @Mapping(source = "hotel.id", target = "hotelId")
    @Mapping(source = "users.id", target = "userId")
    public abstract ReservationDto toDto(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "userId", target = "users", qualifiedByName = "mapUserIdToUsers")
    public abstract void updateEntity(ReservationDto reservationDto, @MappingTarget Reservation reservation);

    @Named("mapUserIdToUsers")
    public Users mapUserIdToUsers(UUID userId) {
        if (userId == null) {
            return null;
        }
        return usersRepository.findById(userId).orElse(null);
    }}
