package com.example.analyticsservice.repository;

import com.example.analyticsservice.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}
