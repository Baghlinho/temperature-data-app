package com.example.authenticationservice.service;

import com.example.authenticationservice.dto.UserDto;
import com.example.authenticationservice.model.User;
import com.example.authenticationservice.repository.UserRepositoryImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepositoryImpl userRepository;

    @PostConstruct
    private void initializeUsers() {
        userRepository.save(User.builder().username("Firas").password("123").bio("scientist").build());
        userRepository.save(User.builder().username("John").password("456").bio("engineer").build());
    }

    public boolean isUserValid(UserDto userDto) {
        Optional<User> user = userRepository.findByUsername(userDto.getUsername());
        return user
                .filter(value -> userDto.getPassword().equals(value.getPassword()))
                .isPresent();
    }
}
