package com.example.demo.user.service;

import com.example.demo.user.dao.UserRepository;
import com.example.demo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id){
        if (!userRepository.existsById(id)){
            throw new IllegalStateException(
                    "there is no user with id: " + id);
        }
        return userRepository.getById(id);
    }

    @Transactional
    public void deleteAll(){
        userRepository.deleteAll();
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void update(User user){
        User userOptional = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "user with id: " + user.getId() + "does not exist"));
        userOptional.setName(user.getName());
        userOptional.setAge(user.getAge());
        userOptional.setEmail(user.getEmail());
    }

    @Transactional
    public void delete(Long userId){
        if (!userRepository.existsById(userId)){
            throw new IllegalStateException(
                    "user with id: " + userId + "does not exist"
            );
        }
        userRepository.deleteById(userId);
    }

}
