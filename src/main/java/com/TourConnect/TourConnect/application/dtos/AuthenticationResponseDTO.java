package com.TourConnect.TourConnect.application.dtos;

import lombok.Getter;

@Getter
public class AuthenticationResponseDTO {
    private String token;

    public AuthenticationResponseDTO(String token) {
        this.token = token;
    }

}
