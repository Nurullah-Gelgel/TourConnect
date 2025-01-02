package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ReservationDto;
import com.TourConnect.TourConnect.domain.entities.Reservation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component

public interface ReservationMapper {

    Reservation toEntity(ReservationDto reservationDto);
    ReservationDto toDto(Reservation reservation);


}
