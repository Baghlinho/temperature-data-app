package com.example.collectionservice.controller;

import com.example.collectionservice.dto.TemperatureDto;
import com.example.collectionservice.service.CollectionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<String> recordTemperature(@RequestBody TemperatureDto temperatureDto,
                                                    HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized temperature recording");
        collectionService.recordTemperature(temperatureDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Successful temperature recording");
    }
}
