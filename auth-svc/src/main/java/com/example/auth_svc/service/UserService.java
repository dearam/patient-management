package com.example.auth_svc.service;

import com.example.auth_svc.Repository.UserRepository;
import com.example.auth_svc.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findByEmail(String email){
        // Placeholder for actual database query logic
        System.out.println("Finding user by email: " + email);
        System.out.println("Finding user by email: " + userRepository.findByEmail(email));
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
