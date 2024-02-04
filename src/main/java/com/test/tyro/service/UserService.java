package com.test.tyro.service;

import com.test.tyro.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional findById(Long id);
    User save(User user);
    Optional<User> delete(User product);
}
