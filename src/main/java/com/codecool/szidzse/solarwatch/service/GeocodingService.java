package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidCityNameException;
import com.codecool.szidzse.solarwatch.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {
    private RestTemplate restTemplate;
    private final String API_KEY = "d5f6786711761d6b846ea4dee00566cb";

    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Location getCoordinates(String cityName) {
        String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", cityName, API_KEY);

        ResponseEntity<Location[]> response = restTemplate.getForEntity(url, Location[].class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().length > 0) {
            return response.getBody()[0];
        } else {
            throw new InvalidCityNameException("Invalid city name: " + cityName + ". Provide a valid city name!");
        }
    }
}
