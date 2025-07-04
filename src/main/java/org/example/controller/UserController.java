package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateUserRequestDto;
import org.example.model.User;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/id/{userId}")
    public User getUserById(@PathVariable("userId") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/user/")
    public User createUser(@RequestBody CreateUserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    @PostMapping("/user/{username}")
    public void createUserByUsername(@PathVariable("username") String username) {
        userService.createUserByUsername(username);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable("userId") Long id) {
        productService.deleteProductsByUserId(id);
        userService.deleteUserById(id);
    }
}