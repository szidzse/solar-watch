package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidDateException;
import com.codecool.szidzse.solarwatch.model.DTO.SolarTimes;
import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetAPIResponseDTO;
import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.model.entity.SunriseSunset;
import com.codecool.szidzse.solarwatch.repository.SunriseSunsetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class SunriseSunsetService {
    private final WebClient webClient;

    private final SunriseSunsetRepository sunriseSunsetRepository;

    @Value("${sunrise-sunset.api.url}")
    private String SUNRISE_SUNSET_API_URL;

    @Autowired
    public SunriseSunsetService(WebClient webClient, SunriseSunsetRepository sunriseSunsetTimeRepository) {
        this.webClient = webClient;
        this.sunriseSunsetRepository = sunriseSunsetTimeRepository;
    }

    public SolarTimes getSolarTimes(BigDecimal lat, BigDecimal lng, LocalDate date) {
        String url = String.format("%s?lat=%s&lng=%s&date=%s", SUNRISE_SUNSET_API_URL, lat, lng, date);

        SunriseSunsetAPIResponseDTO response = webClient
                .get() // request type
                .uri(url) // request URI
                .retrieve() // sends the actual request
                .bodyToMono(SunriseSunsetAPIResponseDTO.class) // parses the response
                .block(); // waits for the response

        if (response != null && "OK".equals(response.status())) {
            return response.results();
        } else {
            throw new InvalidDateException(date);
        }
    }

    public void createAndSaveSunriseSunset(City city, LocalDate date, SolarTimes solarTimes) {
        SunriseSunset sunriseSunset = new SunriseSunset();
        sunriseSunset.setCity(city);
        sunriseSunset.setDate(date);
        sunriseSunset.setSunrise(convertToLocalTime(solarTimes.sunrise()));
        sunriseSunset.setSunset(convertToLocalTime(solarTimes.sunset()));

        sunriseSunsetRepository.save(sunriseSunset);
    }

    private LocalTime convertToLocalTime(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
        return LocalTime.parse(timeStr, formatter);
    }
}
