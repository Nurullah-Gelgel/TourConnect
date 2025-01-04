package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.TourDto;
import com.TourConnect.TourConnect.domain.entities.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TourMapper {

    Tour toEntity(TourDto tourDto);
    TourDto toDto(Tour tour);
    @Mapping(target = "id", ignore = true)
    void updateEntity(TourDto tourDto, @MappingTarget Tour tour);
}
