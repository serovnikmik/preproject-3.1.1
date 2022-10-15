package com.example.demo.user.service;

import com.example.demo.user.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);

    void deleteAll();

}
