package com.example.odev.service;

import com.example.odev.Model.User;
import com.example.odev.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String email, String password, String name, String surname, String birthdate) {
        // Kullanıcıyı kaydetme işlemini gerçekleştir
        User user = new User(email, password, name, surname, birthdate);
        userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        // Kullanıcıyı giriş yapma işlemini gerçekleştir
        User user = userRepository.findByEmailAndPassword(email, password);
        return user != null;
    }
}
