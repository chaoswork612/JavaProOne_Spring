package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CreateUserRequestDto;
import org.example.dto.CreateUserResponseDto;
import org.example.dto.GetUserDto;
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

    public List<GetUserDto> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return users.stream().map(user -> new GetUserDto(user.getId(), user.getUsername())).toList();
    }

    public GetUserDto getUserById(Long id) {
        User user = userRepository.getUserById(id).orElseThrow(EntityNotFoundException::new);
        return new GetUserDto(user.getId(), user.getUsername());
    }

    public GetUserDto getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow(EntityNotFoundException::new);
        return new GetUserDto(user.getId(), user.getUsername());
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        User user =  userRepository.save(Optional.of(new User()).map(u -> {
            u.setUsername(requestDto.getUsername());
            return u;
        }).orElseThrow(EntityNotFoundException::new));
        return new CreateUserResponseDto(user.getId(), user.getUsername());
    }

    public void createUserByUsername(String username) {
        userRepository.insertUserByUsername(username);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
