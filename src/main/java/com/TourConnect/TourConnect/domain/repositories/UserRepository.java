package com.TourConnect.TourConnect.domain.repositories;

import com.TourConnect.TourConnect.domain.entities.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  Optional<Users> findByUsername(String username);
  Users findByEmail(String email);
  Users findByUsernameOrEmail(String username, String email);
  Optional<Users> findById(UUID id);
  List<Users> findAll();
  Users save(Users user);
  void deleteById(UUID id);
  void deleteAll();


}
