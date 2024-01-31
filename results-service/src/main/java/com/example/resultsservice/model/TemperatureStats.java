package com.example.resultsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "temperature_stats")
public class TemperatureStats {
    @Id
    private String id;
    private BigDecimal maximum;
    private BigDecimal minimum;
    private BigDecimal average;
    private BigDecimal median;
    private LocalDateTime timestamp;
}
