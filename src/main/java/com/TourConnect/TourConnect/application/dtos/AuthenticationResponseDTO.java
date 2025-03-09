package com.TourConnect.TourConnect.application.dtos;

import lombok.Getter;

@Getter
public class AuthenticationResponseDTO {
    private String token;
    private UsersDto user; // Kullanıcı bilgilerini de ekleyin

    public AuthenticationResponseDTO(String token, UsersDto user) {
        this.token = token;
        this.user = user;
    }
}
