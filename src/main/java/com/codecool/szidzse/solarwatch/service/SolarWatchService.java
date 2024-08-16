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
    private final SunriseSunsetService solarTimesService;

    @Autowired
    public SolarWatchService(GeocodingService geocodingService, SunriseSunsetService solarTimesService) {
        this.geocodingService = geocodingService;
        this.solarTimesService = solarTimesService;
    }

    public SunriseSunsetDTO getSunriseSunset(String cityName, LocalDate date) {
        City city = geocodingService.getCoordinatesFor(cityName);

        BigDecimal latitude = city.getLatitude();
        BigDecimal longitude = city.getLongitude();

        SolarTimes solarTimes = solarTimesService.getSolarTimes(latitude, longitude, date);

        return new SunriseSunsetDTO(solarTimes.sunrise(), solarTimes.sunset());
    }
}
