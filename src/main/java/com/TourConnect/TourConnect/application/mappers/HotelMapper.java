package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.domain.entities.Hotel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component

public interface HotelMapper {

    Hotel toEntity(HotelDto hotelDto);
    HotelDto toDto(Hotel hotel);
}
