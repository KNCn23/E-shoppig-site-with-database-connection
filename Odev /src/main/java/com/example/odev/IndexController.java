package com.example.odev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; // index.html dosyasını döndürür
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // register.html dosyasını döndürür
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html dosyasını döndürür
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop"; // shop.html dosyasını döndürür
    }

    @GetMapping("/cart")
    public String viewCart() {
        return "cart"; // cart.html dosyasını döndürür
    }

}
