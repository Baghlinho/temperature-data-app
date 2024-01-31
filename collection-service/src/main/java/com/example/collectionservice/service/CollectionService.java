package com.example.collectionservice.service;

import com.example.collectionservice.dto.TemperatureDto;
import com.example.collectionservice.model.Temperature;
import com.example.collectionservice.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectionService {

    private final TemperatureRepository temperatureRepository;
    private final WebClient webClient;

    public void recordTemperature(TemperatureDto temperatureDto) {
        Temperature temperature = Temperature.builder()
                .value(temperatureDto.getValue())
                .build();
        temperatureRepository.save(temperature);
        log.info("Temperature ID={} is saved", temperature.getId());
        notifyAnalyticsService(temperature.getId());
    }

    private void notifyAnalyticsService(long temperatureId) {
        webClient.get()
                .uri("http://analytics:8080/api/analysis")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(
                        response -> log.info("{}: {}", temperatureId, response),
                        error -> log.error("{}: {}", temperatureId,error.getMessage())
                );
    }
}
