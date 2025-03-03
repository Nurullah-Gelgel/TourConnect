package com.TourConnect.TourConnect.application.services;

import com.TourConnect.TourConnect.application.dtos.UsersDto;
import com.TourConnect.TourConnect.application.mappers.UsersMapper;
import com.TourConnect.TourConnect.domain.entities.Users;
import com.TourConnect.TourConnect.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UsersMapper usersMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsersDto> getAllUsers() {
        return userRepository.findAll().stream().map(usersMapper::toDto).collect(Collectors.toList());
    }

    public UsersDto getUserById(UUID id) {
        return userRepository.findById(id).map(usersMapper::toDto).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UsersDto createUser(UsersDto userDto) {
        userDto.setId(null);
        Users user = usersMapper.toEntity(userDto);

        // Şifre hashleme
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Users savedUser = userRepository.save(user);
        return usersMapper.toDto(savedUser);
    }

    public void deleteUser(UUID id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public UsersDto Update(UUID id, UsersDto userDto) {
        Users userToUpdate = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        usersMapper.updateEntity(userDto, userToUpdate);
        return usersMapper.toDto(userRepository.save(userToUpdate));

    }


    public Users getUsersByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        }

    public Users getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users getUsersByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }



}
