package com.example.collectionservice.controller;

import com.example.collectionservice.dto.UserDto;
import com.example.collectionservice.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<String> initiateSession(@RequestBody UserDto userDto, HttpServletRequest req) {
        boolean isAuthenticated = sessionService.authenticateUser(userDto);
        if(isAuthenticated) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userDto);
            return ResponseEntity.ok("Successful session authentication");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed session authentication");
    }
}
