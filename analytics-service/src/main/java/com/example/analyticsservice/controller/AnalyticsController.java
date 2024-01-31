package com.example.analyticsservice.controller;

import com.example.analyticsservice.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String generateTemperatureStats() {
        analyticsService.generateTemperatureStats();
        return "Temperature stats generated successfully";
    }
}
