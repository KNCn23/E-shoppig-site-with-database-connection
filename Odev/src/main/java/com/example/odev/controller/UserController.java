package com.example.odev.controller;

import com.example.odev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        // Başarılı bir şekilde kayıt olduğunda başka bir sayfaya yönlendirme veya mesaj gösterme
        return "redirect:/login"; // Örneğin, kayıt işlemi başarılı olduğunda kullanıcıyı giriş sayfasına yönlendirir
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        if (userService.loginUser(email, password)) {
            // Başarılı bir şekilde giriş yapıldığında başka bir sayfaya yönlendirme veya mesaj gösterme
            return ResponseEntity.ok("redirect:/shop"); // Örneğin, giriş işlemi başarılı olduğunda kullanıcıyı alışveriş sayfasına yönlendirir
        } else {
            // Kullanıcı giriş bilgileri geçersizse, tekrar giriş sayfasına yönlendirme veya hata mesajı gösterme
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("redirect:/login?error=true"); // Örneğin, hata mesajı ile birlikte tekrar giriş sayfasına yönlendirir
        }
    }
}
