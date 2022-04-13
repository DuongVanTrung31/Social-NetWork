package com.codegym.backendjavasocialnetwork.service;

import java.util.Optional;

public interface GenericService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);
}