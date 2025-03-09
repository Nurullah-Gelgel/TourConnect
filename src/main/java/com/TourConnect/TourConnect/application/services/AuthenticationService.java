package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.AuthenticationRequestDTO;
import com.TourConnect.TourConnect.application.dtos.AuthenticationResponseDTO;
import com.TourConnect.TourConnect.application.dtos.UsersDto;
import com.TourConnect.TourConnect.domain.exceptions.UnauthorizedException;
import com.TourConnect.TourConnect.infrastructure.jwt.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationService(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getName());
        if (!new BCryptPasswordEncoder().matches(request.getPassword(), userDetails.getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        String token = jwtTokenProvider.generateToken(userDetails);
        UsersDto userDTO = new UsersDto();
        userDTO.setName(userDetails.getUsername());
        userDTO.setRole(userDetails.getAuthorities().iterator().next().getAuthority());

        return new AuthenticationResponseDTO(token, userDTO);

    }
}
