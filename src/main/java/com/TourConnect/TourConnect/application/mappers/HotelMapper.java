package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.domain.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel toEntity(HotelDto hotelDto);
    HotelDto toDto(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    void updateEntity(HotelDto hotelDto,@MappingTarget Hotel hotel);
}
