package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.TouristPlaceDto;
import com.TourConnect.TourConnect.domain.entities.TouristPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TouristPlaceMapper {

TouristPlace toEntity(TouristPlaceDto touristPlaceDto);
TouristPlaceDto toDto(TouristPlace touristPlace);
@Mapping(target = "id", ignore = true)
void updateEntity(TouristPlaceDto TouristPlaceDto, @MappingTarget TouristPlace touristPlace);
}
