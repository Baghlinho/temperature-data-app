package com.example.resultsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureStatsDto {
    private BigDecimal maximum;
    private BigDecimal minimum;
    private BigDecimal average;
    private BigDecimal median;
    private LocalDateTime timestamp;
}
