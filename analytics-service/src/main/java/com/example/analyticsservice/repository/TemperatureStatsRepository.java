package com.example.analyticsservice.repository;

import com.example.analyticsservice.model.TemperatureStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureStatsRepository extends MongoRepository<TemperatureStats, Long> {
    TemperatureStats findTopByOrderByTimestampDesc();
}
