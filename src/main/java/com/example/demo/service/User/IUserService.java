package com.example.demo.service.User;

import com.example.demo.model.User;
import com.example.demo.service.Generic;

import java.util.Optional;

public interface IUserService  {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void save(User user);

}
