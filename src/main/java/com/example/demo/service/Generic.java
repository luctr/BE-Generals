package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface Generic<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    List<T> findByName(String name);

    void save(T t);

    void delete(Long id);
}
