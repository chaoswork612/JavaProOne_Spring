package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.repository.UserRepository;
import org.example.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        log.info("UserService started");
        log.info("Get user by Id:");
        User user = userRepository.getUserById(1L).stream().findAny().orElseThrow(EntityNotFoundException::new);
        log.info("User by Id: {}", user.toString());
        log.info("Get all users:");
        userRepository.getAllUsers().forEach(u -> log.info("{}", u.toString()));
        log.info("Create new user using username parameter:");
        User newUser = new User();
        newUser.setUsername("john10_doe10");
        userRepository.insertUser(newUser.getUsername());
        log.info(
                "New user with username {} is created",
                userRepository
                        .getUsersByUsername("john10_doe10")
                        .stream().findAny()
                        .orElseThrow()
                        .getUsername()
        );
        userRepository.getAllUsers().forEach(u -> log.info("{}", u.toString()));
        log.info("Delete user by Id:");
        Long userId = user.getId();
        userRepository.deleteUserById(userId);
        log.info("User with id {} is deleted", userId);
        log.info("Get all users:");
        userRepository.getAllUsers().forEach(u -> log.info("{}", u.toString()));
        log.info("Create new user using User Entity:");
        User anotherUser = new User();
        anotherUser.setUsername("john11_doe11");
        userRepository.insertUser(anotherUser);
        log.info(
                "New user with username {} is created",
                userRepository
                        .getUsersByUsername("john11_doe11")
                        .stream().findAny()
                        .orElseThrow()
                        .getUsername()
        );
        log.info("Get all users:");
        userRepository.getAllUsers().forEach(u -> log.info("{}", u.toString()));
        log.info("UserService ended");
    }
}
