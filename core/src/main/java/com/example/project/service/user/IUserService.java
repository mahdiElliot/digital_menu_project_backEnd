package com.example.project.service.user;

import com.example.project.model.user.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    User login(String username, String password);
}
