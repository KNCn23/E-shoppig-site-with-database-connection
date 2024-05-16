package com.example.odev.service;

import com.example.odev.Model.User;
import com.example.odev.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void registerUser(String email, String password, String name, String surname, String birthdate) {
        User user = new User(email, password, name, surname, birthdate);
        userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        return user != null;
    }
}
