package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.RoomDto;
import com.TourConnect.TourConnect.domain.entities.Room;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component

public interface RoomMapper {

    Room toEntity(RoomDto roomDto);
    RoomDto toDto(Room room);
}
