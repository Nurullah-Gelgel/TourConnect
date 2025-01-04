package com.TourConnect.TourConnect.presentation.controllers;

import com.TourConnect.TourConnect.application.dtos.UsersDto;
import com.TourConnect.TourConnect.application.services.UserService;
import com.TourConnect.TourConnect.domain.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")

public class UsersController {

    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UsersDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UsersDto> getUserById(UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/createUser")
    public ResponseEntity<UsersDto> createUser(UsersDto usersDto) {
        return ResponseEntity.ok(userService.createUser(usersDto));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UsersDto> updateUser(UUID id, UsersDto usersDto) {
        return ResponseEntity.ok(userService.Update(id, usersDto));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }
}
