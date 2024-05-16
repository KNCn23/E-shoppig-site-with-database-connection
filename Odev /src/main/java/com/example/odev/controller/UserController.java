package com.example.odev.controller;

import com.example.odev.Model.User;
import com.example.odev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String surname, @RequestParam String birthdate) {
        userService.registerUser(email, password, name, surname, birthdate);
        return "redirect:/login";
    }

    @PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestParam String email, @RequestParam String password) {
        if (userService.loginUser(email, password)) {
            User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getId());
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
