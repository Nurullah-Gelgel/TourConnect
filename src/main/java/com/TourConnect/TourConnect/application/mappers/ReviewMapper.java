package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ReviewDto;
import com.TourConnect.TourConnect.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toEntity(ReviewDto reviewDto);
    ReviewDto toDto(Review review);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ReviewDto reviewDto,@MappingTarget Review review);
}
