package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CreateUserRequestDto;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public User createUser(CreateUserRequestDto requestDto) {
        return userRepository.save(Optional.of(new User()).map(u -> {
            u.setUsername(requestDto.getUsername());
            return u;
        }).orElseThrow(EntityNotFoundException::new));
    }

    public void createUserByUsername(String username) {
        userRepository.insertUserByUsername(username);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
