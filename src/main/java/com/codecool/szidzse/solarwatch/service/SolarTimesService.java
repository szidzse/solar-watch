package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidDateException;
import com.codecool.szidzse.solarwatch.model.SolarTimes;
import com.codecool.szidzse.solarwatch.model.SolarTimesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SolarTimesService {
    private final RestTemplate restTemplate;

    public SolarTimesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SolarTimes getSolarTimes(double lat, double lng, String date) {
        String url = String.format("https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s", lat, lng, date);

        ResponseEntity<SolarTimesResponse> response = restTemplate.getForEntity(url, SolarTimesResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && "OK".equals(response.getBody().status())) {
            return response.getBody().results();
        } else {
            throw new InvalidDateException("Invalid date or date format. Provide a valid date.");
        }
    }
}
