package com.example.authenticationservice.repository;

import com.example.authenticationservice.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
