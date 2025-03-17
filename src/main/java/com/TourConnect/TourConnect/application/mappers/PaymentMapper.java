package com.TourConnect.TourConnect.application.mappers;


import com.TourConnect.TourConnect.application.dtos.PaymentDto;
import com.TourConnect.TourConnect.domain.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);


    @Mapping(source = "reservationId", target = "reservation.id")
    Payment toEntity(PaymentDto paymentRequestDto);

    @Mapping(source = "reservation.id", target = "reservationId")
        PaymentDto toDto(Payment payment);

    @Mapping(source = "reservationId", target = "reservation.id")
    void updateEntity(PaymentDto paymentRequestDto,@MappingTarget Payment payment);

}
