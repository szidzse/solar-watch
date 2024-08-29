package com.codecool.szidzse.solarwatch.model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SunriseSunsetDTO {
    private Long id;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private LocalDate date;
    private Long cityId;
}
