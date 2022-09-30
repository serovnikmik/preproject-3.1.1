package com.example.demo.user.service;

import com.example.demo.user.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);
    public void save(User user);
    public void update(Long id, String name, int age, String email);
    public void delete(Long id);

    public void deleteAll();

}
