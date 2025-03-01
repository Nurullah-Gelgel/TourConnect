package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.RoomDto;
import com.TourConnect.TourConnect.domain.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(source = "hotelId", target = "hotel.id")
    Room toEntity(RoomDto roomDto);

    @Mapping(source =    "hotel.id", target = "hotelId")
    RoomDto toDto(Room room);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "hotelId", target = "hotel.id")
    void updateEntity(RoomDto roomDto, @MappingTarget Room room);

}
