package com.example.authenticationservice.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, E> {
    Optional<T> findById(E id);
    List<T> findAll();
    void save(T t);
    void update(T t);
    void delete(T t);
}
