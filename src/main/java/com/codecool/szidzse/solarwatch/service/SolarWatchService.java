package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.model.DTO.SolarTimes;
import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetDTO;
import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.model.entity.SunriseSunset;
import com.codecool.szidzse.solarwatch.repository.SunriseSunsetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class SolarWatchService {
    private final GeocodingService geocodingService;
    private final SunriseSunsetService sunriseSunsetService;
    private final SunriseSunsetRepository sunriseSunsetRepository;

    @Autowired
    public SolarWatchService(GeocodingService geocodingService, SunriseSunsetService sunriseSunsetService, SunriseSunsetRepository sunriseSunsetRepository) {
        this.geocodingService = geocodingService;
        this.sunriseSunsetService = sunriseSunsetService;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
    }

    public SunriseSunsetDTO getSunriseSunset(String cityName, LocalDate date) {
        City city = geocodingService.getCoordinatesFor(cityName);
        BigDecimal latitude = city.getLatitude();
        BigDecimal longitude = city.getLongitude();
        SolarTimes solarTimes = sunriseSunsetService.getSolarTimes(latitude, longitude, date);

        return new SunriseSunsetDTO(solarTimes.sunrise(), solarTimes.sunset());
    }
}
