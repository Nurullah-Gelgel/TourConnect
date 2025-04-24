package com.TourConnect.TourConnect.application.mappers;

import com.TourConnect.TourConnect.application.dtos.HotelDto;
import com.TourConnect.TourConnect.domain.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(source = "advancePayment", target = "advancePayment")
    @Mapping(source = "photoUrls", target = "photoUrls")
    Hotel toEntity(HotelDto hotelDto);

    @Mapping(source = "advancePayment", target = "advancePayment")
    @Mapping(source = "photoUrls", target = "photoUrls")
    HotelDto toDto(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "advancePayment", target = "advancePayment")
    @Mapping(source = "photoUrls", target = "photoUrls")
    void updateEntity(HotelDto hotelDto, @MappingTarget Hotel hotel);

    default List<String> mapPhotoUrls(List<String> value) {
        return value != null ? new ArrayList<>(value) : new ArrayList<>();
    }
}