package com.TourConnect.TourConnect.application.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    private UUID contactId;
    private String email;
    private String subject;
    private LocalDate date;
    private String status;
    private String phone;
    private String message;
    private String createdAt;
    private String updatedAt;

    private UUID userId;
}
