package com.TourConnect.TourConnect.presentation.controllers;


import com.TourConnect.TourConnect.application.dtos.AuthenticationRequestDTO;
import com.TourConnect.TourConnect.application.dtos.AuthenticationResponseDTO;
import com.TourConnect.TourConnect.application.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public AuthenticationResponseDTO login(@RequestBody AuthenticationRequestDTO authRequest) {
        return authService.authenticate(authRequest);
    }
}