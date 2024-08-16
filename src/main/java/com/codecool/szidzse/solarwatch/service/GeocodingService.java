package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidCityNameException;
import com.codecool.szidzse.solarwatch.model.DTO.GeocodingAPIResponseDTO;
import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class GeocodingService {
    private final WebClient webClient;

    private final CityRepository cityRepository;

    @Value("${geocoding.api.url}")
    private String GEOCODING_API_URL;

    @Value("${geocoding.api.key}")
    private String GEOCODING_API_KEY;

    @Autowired
    public GeocodingService(WebClient webClient, CityRepository cityRepository) {
        this.webClient = webClient;
        this.cityRepository = cityRepository;
    }

    public City getCoordinatesFor(String cityName) {
        return cityRepository.findByNameIgnoreCase(cityName)
                .orElseGet(() -> {
                    GeocodingAPIResponseDTO responseDTO = fetchCoordinatesFor(cityName);
                    City fetchedCity = new City(null, responseDTO.name(), responseDTO.state(),
                            responseDTO.country(), responseDTO.lon(), responseDTO.lat());
                    return cityRepository.save(fetchedCity);
                });
    }


    private GeocodingAPIResponseDTO fetchCoordinatesFor(String cityName) {
        String url = String.format("%s?q=%s&appid=%s", GEOCODING_API_URL, cityName, GEOCODING_API_KEY);

        GeocodingAPIResponseDTO[] responseArray = webClient
                .get() // request type
                .uri(url) // request URI
                .retrieve() // sends the actual request
                .bodyToMono(GeocodingAPIResponseDTO[].class) // parses the response
                .block(); // waits for the response

        if (responseArray != null && responseArray.length > 0) {
            return responseArray[0];
        } else {
            throw new InvalidCityNameException(cityName);
        }
    }
}
