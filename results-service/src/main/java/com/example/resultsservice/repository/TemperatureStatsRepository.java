package com.example.resultsservice.repository;

import com.example.resultsservice.model.TemperatureStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureStatsRepository extends MongoRepository<TemperatureStats, Long> {
    TemperatureStats findTopByOrderByTimestampDesc();
}
