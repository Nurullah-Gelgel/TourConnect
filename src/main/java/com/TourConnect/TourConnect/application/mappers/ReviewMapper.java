package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ReviewDto;
import com.TourConnect.TourConnect.domain.entities.Review;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component

public interface ReviewMapper {

    Review toEntity(ReviewDto reviewDto);
    ReviewDto toDto(Review review);
}
