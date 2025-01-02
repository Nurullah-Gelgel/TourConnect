package com.TourConnect.TourConnect.application.dtos;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {


    private UUID id;

    private String name;

    private String email;

    private String password;

    private String role;

    private String languagePreference;

    private String createdAt;

    private String updatedAt;
}
