package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.model.DTO.SolarTimes;
import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetDTO;
import com.codecool.szidzse.solarwatch.model.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SolarWatchService {
    private final GeocodingService geocodingService;
    private final SunriseSunsetService sunriseSunsetService;

    @Autowired
    public SolarWatchService(GeocodingService geocodingService, SunriseSunsetService sunriseSunsetService) {
        this.geocodingService = geocodingService;
        this.sunriseSunsetService = sunriseSunsetService;
    }

    public SunriseSunsetDTO getSunriseSunset(String cityName, LocalDate date) {
        City city = geocodingService.getCoordinatesFor(cityName);
        BigDecimal latitude = city.getLatitude();
        BigDecimal longitude = city.getLongitude();
        SolarTimes solarTimes = sunriseSunsetService.getSolarTimes(latitude, longitude, date);

        sunriseSunsetService.createAndSaveSunriseSunset(city, date, solarTimes);

        return new SunriseSunsetDTO(solarTimes.sunrise(), solarTimes.sunset());
    }
}
