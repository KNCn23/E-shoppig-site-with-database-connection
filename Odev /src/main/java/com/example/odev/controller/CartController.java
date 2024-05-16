package com.example.odev.controller;

import com.example.odev.Model.CartItem;
import com.example.odev.Model.User;
import com.example.odev.repo.CartItemRepository;
import com.example.odev.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        User user = userRepository.findById(cartItem.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found with ID: " + cartItem.getUser().getId()));
        cartItem.setUser(user);
        return cartItemRepository.save(cartItem);
    }

    @PostMapping("/addOrUpdate")
    public CartItem addOrUpdateCartItem(@RequestBody CartItem cartItem) {
        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(cartItem.getUser().getId(), cartItem.getProduct().getId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            return cartItemRepository.save(existingCartItem);
        }
        return cartItemRepository.save(cartItem);
    }

    @GetMapping("/list/{userId}")
    public List<CartItem> listCartItems(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return cartItemRepository.findByUserId(user.getId());
    }

    @DeleteMapping("/remove/{cartItemId}")
    public void removeFromCart(@PathVariable Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
