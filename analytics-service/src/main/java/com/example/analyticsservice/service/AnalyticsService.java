package com.example.analyticsservice.service;

import com.example.analyticsservice.model.Temperature;
import com.example.analyticsservice.model.TemperatureStats;
import com.example.analyticsservice.repository.TemperatureRepository;
import com.example.analyticsservice.repository.TemperatureStatsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.DoubleStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticsService {

    private final TemperatureRepository temperatureRepository;
    private final TemperatureStatsRepository temperatureStatsRepository;

    @Transactional
    public void generateTemperatureStats(){
        List<Temperature> temperatures = temperatureRepository.findAll();
        TemperatureStats temperatureStats = TemperatureStats.builder()
                .average(computeAverage(temperatures))
                .minimum(computeMinimum(temperatures))
                .median(computeMedian(temperatures))
                .maximum(computeMaximum(temperatures))
                .timestamp(temperatures.get(temperatures.size()-1).getCreatedOn())
                .build();
        temperatureStatsRepository.save(temperatureStats);
        log.info("Temperature stats {} is saved", temperatureStats.getId());
    }

    private BigDecimal computeMaximum(List<Temperature> temperatures) {
        double maximum = temperatures.stream()
                .mapToDouble(Temperature::getValue)
                .max().orElseThrow(NoSuchElementException::new);
        return BigDecimal.valueOf(maximum);
    }

    private BigDecimal computeMinimum(List<Temperature> temperatures) {
        double minimum = temperatures.stream()
                .mapToDouble(Temperature::getValue)
                .min().orElseThrow(NoSuchElementException::new);
        return BigDecimal.valueOf(minimum);
    }

    private BigDecimal computeAverage(List<Temperature> temperatures) {
        double average = temperatures.stream()
                .mapToDouble(Temperature::getValue)
                .average().orElseThrow(NoSuchElementException::new);
        return BigDecimal.valueOf(average);
    }

    private BigDecimal computeMedian(List<Temperature> temperatures) {
        DoubleStream sortedValues = temperatures.stream().mapToDouble(Temperature::getValue).sorted();
        double median =  temperatures.size()%2 == 0?
                sortedValues.skip(temperatures.size()/2-1).limit(2).average().orElseThrow(NoSuchElementException::new):
                sortedValues.skip(temperatures.size()/2).findFirst().orElseThrow(NoSuchElementException::new);
        return BigDecimal.valueOf(median);
    }
}
