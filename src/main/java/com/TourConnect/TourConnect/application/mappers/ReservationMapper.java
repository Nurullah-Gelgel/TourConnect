package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.domain.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation toEntity(ReservationDto reservationDto);
    ReservationDto toDto(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ReservationDto reservationDto,@MappingTarget Reservation reservation);

}
