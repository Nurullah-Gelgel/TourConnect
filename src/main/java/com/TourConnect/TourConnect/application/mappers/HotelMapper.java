package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.domain.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(source = "advancePayment", target = "advancePayment")
    Hotel toEntity(HotelDto hotelDto);

    @Mapping(source = "advancePayment", target = "advancePayment")
    HotelDto toDto(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "advancePayment", target = "advancePayment")
    void updateEntity(HotelDto hotelDto, @MappingTarget Hotel hotel);
}
