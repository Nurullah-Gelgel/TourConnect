package com.TourConnect.TourConnect.presentation.controllers;


import com.TourConnect.TourConnect.application.dtos.AuthenticationRequestDTO;
import com.TourConnect.TourConnect.application.dtos.AuthenticationResponseDTO;
import com.TourConnect.TourConnect.application.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public AuthenticationResponseDTO login(@RequestBody AuthenticationRequestDTO authRequest) {
        return authService.authenticate(authRequest);
    }
}