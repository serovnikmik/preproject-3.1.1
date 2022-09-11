package com.example.demo.user.service;

import com.example.demo.user.dao.UserRepository;
import com.example.demo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        if (!userRepository.existsById(id)){
            throw new IllegalStateException(
                    "there is no user with id: " + id);
        }
        return userRepository.getById(id);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public void addExamples(){
        User user1 = new User("Name 1", 11, "1@gmail.com");
        User user2 = new User("Name 2", 12, "2@gmail.com");
        User user3 = new User("Name 3", 13, "3@gmail.com");
        this.save(user1);
        this.save(user2);
        this.save(user3);
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.saveAll(List.of(user1, user2, user3));
    }

    public void save(User user){
        if (user.getId() != null && userRepository.existsById(user.getId())){
            throw new IllegalStateException(
                    "user with id: " + user.getId() + "already exists");
        } else {
            userRepository.save(user);
        }
    }

    @Transactional
    public void update(Long userId, String name, int age, String email){
        User userOptional = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id: " + userId + "does not exist"));
        userOptional.setName(name);
        userOptional.setAge(age);
        userOptional.setEmail(email);
    }

    public void delete(Long userId){
        if (!userRepository.existsById(userId)){
            throw new IllegalStateException(
                    "user with id: " + userId + "does not exist"
            );
        }
        userRepository.deleteById(userId);
    }

}
