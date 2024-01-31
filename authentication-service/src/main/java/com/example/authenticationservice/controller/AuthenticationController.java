package com.example.authenticationservice.controller;

import com.example.authenticationservice.dto.UserDto;
import com.example.authenticationservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean authenticateUser(@RequestBody UserDto userDto) {
        return authenticationService.isUserValid(userDto);
    }
}
