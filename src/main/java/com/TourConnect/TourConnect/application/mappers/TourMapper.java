package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.TourDto;
import com.TourConnect.TourConnect.domain.entities.Tour;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component

public interface TourMapper {

    Tour toEntity(TourDto tourDto);
    TourDto toDto(Tour tour);
}
