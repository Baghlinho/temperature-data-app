package com.example.resultsservice.service;

import com.example.resultsservice.dto.TemperatureStatsDto;
import com.example.resultsservice.model.TemperatureStats;
import com.example.resultsservice.repository.TemperatureStatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultsService {

    private final TemperatureStatsRepository temperatureStatsRepository;

    public TemperatureStatsDto getMostRecentTemperatureStats() {
        TemperatureStats temperatureStats = temperatureStatsRepository.findTopByOrderByTimestampDesc();
        if (temperatureStats == null) return null;
        return TemperatureStatsDto.builder()
                .average(temperatureStats.getAverage())
                .maximum(temperatureStats.getMaximum())
                .minimum(temperatureStats.getMinimum())
                .median(temperatureStats.getMedian())
                .timestamp(temperatureStats.getTimestamp())
                .build();
    }
}
