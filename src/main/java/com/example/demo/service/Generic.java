package com.example.demo.service;

import java.util.Optional;

public interface Generic<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void delete(Long id);
}
