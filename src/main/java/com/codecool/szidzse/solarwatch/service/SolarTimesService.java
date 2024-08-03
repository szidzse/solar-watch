package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidDateException;
import com.codecool.szidzse.solarwatch.model.SolarTimes;
import com.codecool.szidzse.solarwatch.model.SolarTimesResponse;
import com.codecool.szidzse.solarwatch.repository.SunriseSunsetTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
public class SolarTimesService {
    private final WebClient webClient;

    private SunriseSunsetTimeRepository sunriseSunsetTimeRepository;

    @Value("${sunrise-sunset.api.url}")
    private String SUNRISE_SUNSET_API_URL;

    @Autowired
    public SolarTimesService(WebClient webClient, SunriseSunsetTimeRepository sunriseSunsetTimeRepository) {
        this.webClient = webClient;
        this.sunriseSunsetTimeRepository = sunriseSunsetTimeRepository;
    }

    public SolarTimes getSolarTimes(double lat, double lng, LocalDate date) {
        String url = String.format("%s?lat=%s&lng=%s&date=%s", SUNRISE_SUNSET_API_URL, lat, lng, date);

        SolarTimesResponse response = webClient
                .get() // request type
                .uri(url) // request URI
                .retrieve() // sends the actual request
                .bodyToMono(SolarTimesResponse.class) // parses the response
                .block(); // waits for the response

        if (response != null && "OK".equals(response.status())) {
            return response.results();
        } else {
            throw new InvalidDateException(date);
        }
    }
}
