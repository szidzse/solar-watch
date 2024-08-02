package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetDTO;
import com.codecool.szidzse.solarwatch.model.SolarTimes;
import com.codecool.szidzse.solarwatch.model.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SolarWatchService {
    private GeocodingService geocodingService;
    private  SolarTimesService solarTimesService;

    @Autowired
    public SolarWatchService(GeocodingService geocodingService, SolarTimesService solarTimesService) {
        this.geocodingService = geocodingService;
        this.solarTimesService = solarTimesService;
    }

    public SunriseSunsetDTO getSunriseSunset(String cityName, LocalDate date) {
        City city = geocodingService.getCoordinatesFor(cityName);

        double latitude = city.getLatitude();
        double longitude = city.getLongitude();

        SolarTimes solarTimes = solarTimesService.getSolarTimes(latitude, longitude, date);

        return new SunriseSunsetDTO(solarTimes.sunrise(), solarTimes.sunset());
    }
}
