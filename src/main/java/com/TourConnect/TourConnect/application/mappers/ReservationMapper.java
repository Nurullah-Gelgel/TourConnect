package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.domain.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "userId", target = "users.id")
    @Mapping(source = "roomId", target = "room.id")
    Reservation toEntity(ReservationDto reservationDto);

    @Mapping(source =    "hotel.id", target = "hotelId")
    @Mapping(source =    "users.id", target = "userId")
    @Mapping(source =    "room.id", target = "roomId")
    ReservationDto toDto(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "userId", target = "users.id")
    @Mapping(source = "roomId", target = "room.id")
    void updateEntity(ReservationDto reservationDto,@MappingTarget Reservation reservation);

}
