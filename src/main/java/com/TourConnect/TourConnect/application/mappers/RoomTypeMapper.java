package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.RoomTypeDto;
import com.TourConnect.TourConnect.domain.entities.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

    @Mapping(source ="hotel.id", target = "hotelId")
    RoomTypeDto toDto(RoomType roomType);

    @Mapping(source = "hotelId", target = "hotel.id")
    RoomType toEntity(RoomTypeDto roomTypeDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "hotelId", target = "hotel.id")
    void updateEntity(RoomTypeDto roomTypeDTO, @MappingTarget RoomType roomType);


}
