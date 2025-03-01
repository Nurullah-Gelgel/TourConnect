package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.ReviewDto;
import com.TourConnect.TourConnect.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "userId", target = "users.id")
    Review toEntity(ReviewDto reviewDto);

    @Mapping(source =    "hotel.id", target = "hotelId")
    @Mapping(source =    "users.id", target = "userId")
    ReviewDto toDto(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "userId", target = "users.id")
    void updateEntity(ReviewDto reviewDto,@MappingTarget Review review);
}
