package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidDateException;
import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetAPIResponseDTO;
import com.codecool.szidzse.solarwatch.model.SolarTimes;
import com.codecool.szidzse.solarwatch.model.SolarTimesResponse;
import com.codecool.szidzse.solarwatch.repository.SunriseSunsetTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class SolarTimesService {
    private final RestTemplate restTemplate;

    private SunriseSunsetTimeRepository sunriseSunsetTimeRepository;

    @Value("${sunrise-sunset.api.url}")
    private String SUNRISE_SUNSET_API_URL;

    @Autowired
    public SolarTimesService(RestTemplate restTemplate, SunriseSunsetTimeRepository sunriseSunsetTimeRepository) {
        this.restTemplate = restTemplate;
        this.sunriseSunsetTimeRepository = sunriseSunsetTimeRepository;
    }

    public SolarTimes getSolarTimes(double lat, double lng, LocalDate date) {
        String url = String.format("%s?lat=%s&lng=%s&date=%s", SUNRISE_SUNSET_API_URL, lat, lng, date);

        ResponseEntity<SolarTimesResponse> response = restTemplate.getForEntity(url, SolarTimesResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && "OK".equals(response.getBody().status())) {
            return response.getBody().results();
        } else {
            throw new InvalidDateException(date);
        }
    }
}
