package com.example.authenticationservice.repository;

import com.example.authenticationservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
        int id = users.indexOf(user);
        if(id == -1)
            return;
        users.get(id).setUsername(user.getUsername());
        users.get(id).setPassword(user.getPassword());
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}
