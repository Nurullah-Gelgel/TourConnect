package com.TourConnect.TourConnect.infrastructure.repositories.impl;

import com.TourConnect.TourConnect.domain.entities.Users;
import com.TourConnect.TourConnect.domain.repositories.UserRepository;
import com.TourConnect.TourConnect.infrastructure.repositories.jpa.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserServiceImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return jpaUserRepository.findAll().stream()
                .filter(user -> user.getName().equals(username))
                .findFirst();
    }

    @Override
    public Users findByEmail(String email) {
        return null;
    }

    @Override
    public Users findByUsernameOrEmail(String username, String email) {
        return null;
    }

    @Override
    public Optional<Users> findById(UUID id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public Users save(Users user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        jpaUserRepository.deleteAll();
    }
}
