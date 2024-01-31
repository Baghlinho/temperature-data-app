package com.example.resultsservice.controller;

import com.example.resultsservice.dto.TemperatureStatsDto;
import com.example.resultsservice.service.ResultsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
public class ResultsController {

    private final ResultsService resultsService;

    @GetMapping
    public ResponseEntity<TemperatureStatsDto> getMostRecentTemperatureStats(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        return ResponseEntity.ok(resultsService.getMostRecentTemperatureStats());
    }
}
