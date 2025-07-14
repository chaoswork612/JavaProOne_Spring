package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateUserRequestDto;
import org.example.dto.CreateUserResponseDto;
import org.example.dto.GetUserDto;
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
    public List<GetUserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{userId}")
    public GetUserDto getUserById(@PathVariable("userId") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public GetUserDto getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/")
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    @PostMapping("/{username}")
    public void createUserByUsername(@PathVariable("username") String username) {
        userService.createUserByUsername(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long id) {
        productService.deleteProductsByUserId(id);
        userService.deleteUserById(id);
    }
}